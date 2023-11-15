package com.onxshield.invoiceyou.invoicestatement.service;


import com.onxshield.invoiceyou.invoicestatement.dto.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.request.invoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.request.updateInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.basicInvoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.dto.response.merchandiseResponse;
import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import com.onxshield.invoiceyou.invoicestatement.model.*;
import com.onxshield.invoiceyou.invoicestatement.repository.*;
import com.onxshield.invoiceyou.invoicestatement.util.numberToWordUtil;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
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
        AtomicReference<Double> totalTTC = new AtomicReference<>(0D); //todo what's this ? suggested by IDE, and it works fine hh

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
                            merchandise merchandiseToSave = null;
                            Optional<inventory> inventory = inventoryRepository.findByProductProductId(merchandiseRequest.productId());
                            Double availability = inventory.get().getAvailability();
                            if(availability > merchandiseRequest.quantity() && availability > 0){
                                Double totalByProduct = merchandiseRequest.quantity() * inventory.get().getSellPrice();
                                totalTTC.updateAndGet(v -> v + totalByProduct.longValue());
                                inventory.get().setAvailability(availability - merchandiseRequest.quantity());
                                merchandiseToSave = new merchandise();
                                merchandiseToSave.setProduct(productRepository.findById(merchandiseRequest.productId()).get());
                                merchandiseToSave.setQuantity(merchandiseRequest.quantity());
                                merchandiseToSave.setTotal(totalByProduct);
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

            return invoiceRepository.save(invoiceToSave);
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
        if(invoiceRepository.findById(request.invoiceId()).isEmpty()){
            invoice invoice = new invoice();
            invoice.setInvoiceId(request.invoiceId());
            invoice.setInvoiceDate(request.invoiceDate());
            invoice.setClient(clientRepository.findById(request.clientId()).get());
            invoice.setTotalTTC(request.totalTTC());
            invoice.setSpelledTotal(numberToWordUtil.convert(request.totalTTC()));
            invoice.setTVA(request.totalTTC().doubleValue()/6);
            invoice.setPaymentMethod(paymentMethod.valueOf(request.paymentMethod()));
            invoice.setBankName(request.bankName());
            invoice.setCheckNumber(request.checkNumber());
            invoice.setPaymentDate(request.paymentDate());

            return invoiceRepository.save(invoice);
        }
        else throw new requestException("Invoice already exist",HttpStatus.CONFLICT);
    }

    public invoice updateInvoiceById(updateInvoiceRequest request) {
        Optional<invoice> invoice = invoiceRepository.findById(request.basicInvoice().invoiceId());
        if (invoice.isPresent()){
            Optional<client> client = clientRepository.findById(request.basicInvoice().clientId());
            invoice.get().setClient(client.get());
            invoice.get().setInvoiceDate(request.basicInvoice().invoiceDate());
            invoice.get().setTotalTTC(request.basicInvoice().totalTTC());
            invoice.get().setPaymentMethod(paymentMethod.valueOf(request.basicInvoice().paymentMethod()));
            invoice.get().setBankName(request.basicInvoice().bankName());
            invoice.get().setCheckNumber(request.basicInvoice().checkNumber());
            invoice.get().setPaymentDate(request.basicInvoice().paymentDate());
            invoice.get().setPrinted(request.printed());
            invoice.get().setInvoiceAction(action.valueOf(request.invoiceAction()));
            invoice.get().setInvoiceStatus(status.valueOf(request.invoiceStatus()));
            invoice.get().setInvoiceFile(request.invoiceFile());
            return invoiceRepository.save(invoice.get());
        }else throw
                new requestException("The invoice id you provided doesn't exist",HttpStatus.NOT_FOUND);
    }
}
