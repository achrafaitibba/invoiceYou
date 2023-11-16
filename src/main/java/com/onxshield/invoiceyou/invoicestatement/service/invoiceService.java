package com.onxshield.invoiceyou.invoicestatement.service;


import com.onxshield.invoiceyou.invoicestatement.dto.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.request.invoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.basicInvoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.dto.response.merchandiseResponse;
import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import com.onxshield.invoiceyou.invoicestatement.model.*;
import com.onxshield.invoiceyou.invoicestatement.repository.*;
import com.onxshield.invoiceyou.invoicestatement.util.numberToWordUtil;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
@Transactional
public class invoiceService {

    private final invoiceRepository invoiceRepository;
    private final clientRepository clientRepository;
    private final inventoryRepository inventoryRepository;
    private final productRepository productRepository;
    private final merchandiseRepository merchandiseRepository;
    private final invoiceNumberRepository invoiceNumberRepository;
    static long latestInvoiceNumber = 1225;
    public invoice createBasicInvoice(basicInvoiceRequest request) {
        Optional<client> client = clientRepository.findById(request.clientId());
        AtomicReference<Double> totalTTC = new AtomicReference<>(0D);
        List<merchandise> savedMerchandise = new ArrayList<>();
        //find inventory by product id
        //check availability
        //get the sell price
        //calculate total by product = sell price * quantity
        //add the total by product to totalTTC
        //decrease availability in the inventory
        //BUILD the merchandise instances
        List<merchandise> merchandiseList = request.merchandiseList().stream()
                .map(

                        merchandiseRequest -> {
                            merchandise merchandiseToSave ;
                            Optional<inventory> inventory = inventoryRepository.findByProductProductId(merchandiseRequest.productId());
                            Double availability = inventory.get().getAvailability();
                            if(availability >= merchandiseRequest.quantity() && availability > 0){
                                Double totalByProduct = merchandiseRequest.quantity() * inventory.get().getSellPrice();
                                totalTTC.updateAndGet(v -> v + totalByProduct.longValue());
                                inventory.get().setAvailability(availability - merchandiseRequest.quantity());
                                merchandiseToSave = new merchandise();
                                merchandiseToSave.setProduct(productRepository.findById(merchandiseRequest.productId()).get());
                                merchandiseToSave.setQuantity(merchandiseRequest.quantity());
                                merchandiseToSave.setTotal(totalByProduct);
                                savedMerchandise.add(merchandiseToSave);
                                return merchandiseRepository.save(merchandiseToSave);
                            }else {
                                throw new requestException("Product isn't available in the inventory, out of stock", HttpStatus.CONFLICT);
                            }


                        }
                )
                .toList()
                ;

        if(invoiceRepository.findById(request.invoiceId()).isEmpty()){
            invoice invoiceToSave = new invoice();
            invoiceToSave.setInvoiceId(request.invoiceId());
            invoiceToSave.setInvoiceDate(request.invoiceDate());
            invoiceToSave.setDiscount(request.discount());
            invoiceToSave.setClient(client.get());
            invoiceToSave.setTotalTTC(totalTTC.get().longValue());
            invoiceToSave.setTVA(totalTTC.get() /6);
            invoiceToSave.setSpelledTotal(convertNumberToWords(totalTTC.get().longValue()));
            invoiceToSave.setMerchandiseList(merchandiseList);
            invoiceToSave.setCheckNumber(request.checkNumber());
            invoiceToSave.setPaymentMethod(paymentMethod.valueOf(request.paymentMethod()));
            invoice saved = invoiceRepository.save(invoiceToSave);
            for (merchandise merch: savedMerchandise
                 ) {
                Optional<merchandise> toUpdate = merchandiseRepository.findById(merch.getMerchId());
                toUpdate.get().setInvoice(saved);
            }
            return saved;
        }
        else {
            throw new requestException("The Id you provided already used by other invoice",HttpStatus.CONFLICT);
        }


    }

    public String convertNumberToWords(Long total) {
        return numberToWordUtil.convert(total).toUpperCase();
    }

    public String[] getAvailableInvoiceNumbers() {
        List<String> numbers = invoiceNumberRepository.findAll()
                .stream()
                .map(invoiceNumber::getInvoiceNumber)
                .toList();
        return numbers.toArray(new String[0]);
    }

    public String generateInvoiceNumber() {
        int currentYear = Year.now().getValue();
        int lastTwoDigits = currentYear % 100;
        latestInvoiceNumber++;
        String toSave = "ST"+latestInvoiceNumber+"/"+String.format("%02d", lastTwoDigits);
        invoiceNumberRepository.save(new invoiceNumber(toSave));
        latestInvoiceNumber++;
        return "ST"+latestInvoiceNumber+"/"+String.format("%02d", lastTwoDigits);

    }

    public invoice getInvoiceById(String invoiceId) {
        Optional<invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()){
            return invoice.get();
        }
        else throw new requestException("The Id you provided doesn't exist",HttpStatus.CONFLICT);
    }

    public basicInvoiceResponse getBasicInvoiceById(String invoiceId) {
        invoice invoice = getInvoiceById(invoiceId);
        return new basicInvoiceResponse(
                invoice.getClient().getName(),
                invoice.getInvoiceId(),
                invoice.getClient().getICE(),
                invoice.getInvoiceDate(),
                invoice.getMerchandiseList().stream().map(

                        merchandise -> new merchandiseResponse(
                                merchandise.getMerchId(),
                                merchandise.getProduct().getName(),
                                merchandise.getProduct().getUnit().toString(),
                                merchandise.getQuantity(),
                                inventoryRepository.findByProductProductId(merchandise.getProduct().getProductId()).get().getSellPrice(),
                                merchandise.getQuantity().longValue() * inventoryRepository.findByProductProductId(merchandise.getProduct().getProductId()).get().getSellPrice().longValue()
                        )
                )
                        .toList(),
                invoice.getTotalTTC(),
                invoice.getTVA(),
                invoice.getSpelledTotal(),
                invoice.getPaymentMethod().toString(),
                invoice.getCheckNumber(),
                invoice.getDiscount()
        );
    }

    public List<invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public invoice createInvoice(invoiceRequest request) {
        AtomicReference<Double> totalTTC = new AtomicReference<>(0D);
        List<merchandise> savedMerchandise = new ArrayList<>();

        if(invoiceRepository.findById(request.invoiceId()).isEmpty()){
            Optional<client> client = clientRepository.findById(request.clientId());
            List<merchandise> merchandiseList = request.merchandiseList().stream()
                    .map(

                            merchandiseRequest -> {
                                merchandise merchandiseToSave ;
                                Optional<inventory> inventory = inventoryRepository.findByProductProductId(merchandiseRequest.productId());
                                Double availability = inventory.get().getAvailability();
                                if(availability >= merchandiseRequest.quantity() && availability > 0){
                                    Double totalByProduct = merchandiseRequest.quantity() * inventory.get().getSellPrice();
                                    totalTTC.updateAndGet(v -> v + totalByProduct.longValue());
                                    inventory.get().setAvailability(availability - merchandiseRequest.quantity());
                                    merchandiseToSave = new merchandise();
                                    merchandiseToSave.setProduct(productRepository.findById(merchandiseRequest.productId()).get());
                                    merchandiseToSave.setQuantity(merchandiseRequest.quantity());
                                    merchandiseToSave.setTotal(totalByProduct);
                                    savedMerchandise.add(merchandiseToSave);
                                    return merchandiseRepository.save(merchandiseToSave);
                                }else {
                                    throw new requestException("Product isn't available in the inventory, out of stock", HttpStatus.CONFLICT);
                                }
                            }
                    )
                    .toList()
                    ;
            invoice invoiceToSave = invoice.builder()
                    .invoiceId(request.invoiceId())
                    .invoiceDate(request.invoiceDate())
                    .client(client.get())
                    .totalTTC(request.totalTTC())
                    .TVA(request.totalTTC().doubleValue()/6)
                    .spelledTotal(numberToWordUtil.convert(request.totalTTC()))
                    .paymentMethod(paymentMethod.valueOf(request.paymentMethod()))
                    .bankName(request.bankName())
                    .checkNumber(request.checkNumber())
                    .paymentDate(request.paymentDate())
                    .printed(Boolean.valueOf(request.printed()))
                    .invoiceAction(action.valueOf(request.invoiceAction()))
                    .invoiceStatus(status.valueOf(request.invoiceStatus()))
                    .invoiceFile(request.invoiceFile())
                    .merchandiseList(merchandiseList)
                    .build();
            invoice saved = invoiceRepository.save(invoiceToSave);
            for (merchandise merch: savedMerchandise
            ) {
                Optional<merchandise> toUpdate = merchandiseRepository.findById(merch.getMerchId());
                toUpdate.get().setInvoice(saved);
            }
            return saved;

        }
        else throw new requestException("Invoice already exist",HttpStatus.CONFLICT);
    }


}
