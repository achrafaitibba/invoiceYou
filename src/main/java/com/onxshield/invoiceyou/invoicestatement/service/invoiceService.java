package com.onxshield.invoiceyou.invoicestatement.service;


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
import java.util.List;
import java.util.Optional;


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
    public invoice createBasicInvoice(invoiceRequest request) {
        //find inventory by product id
        //check availability
        //get the sell price
        //calculate total by product = sell price * quantity
        //add the total by product to totalTTC
        //decrease availability in the inventory
        //BUILD the merchandise instances

        Optional<client> client = clientRepository.findById(request.clientId());
        if(invoiceRepository.findById(request.invoiceId()).isEmpty()){
            deleteInvoiceNumberByInvoiceNumber(request.invoiceId());
            List<merchandise> savedMerchandise = merchandiseRequestToMerchandise(request);
            invoice invoiceToSave = new invoice();
            invoiceToSave.setInvoiceId(request.invoiceId());
            invoiceToSave.setInvoiceDate(request.invoiceDate());
            invoiceToSave.setClient(client.get());
            invoiceToSave.setTotalTTC(request.totalTTC());
            invoiceToSave.setTVA(request.totalTTC().doubleValue() /6);
            invoiceToSave.setSpelledTotal(convertNumberToWords(request.totalTTC().longValue()));
            invoiceToSave.setMerchandiseList(savedMerchandise);
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

    //todo, if an ID is used, delete from invoiceNumber
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

    public List<merchandise> merchandiseRequestToMerchandise(invoiceRequest request){
        return request.merchandiseList().stream()
                .map(

                        merchandiseRequest -> {
                            merchandise merchandiseToSave ;
                            Optional<inventory> inventory = inventoryRepository.findByProductProductId(merchandiseRequest.productId());
                            Double availability = inventory.get().getAvailability();
                            if(availability >= merchandiseRequest.quantity() && availability > 0){
                                Double totalByProduct = merchandiseRequest.quantity() * inventory.get().getSellPrice();
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
                .toList();
    }

    public invoice createInvoice(invoiceRequest request) {
        Optional<client> client = clientRepository.findById(request.clientId());
        if(invoiceRepository.findById(request.invoiceId()).isEmpty()){
            deleteInvoiceNumberByInvoiceNumber(request.invoiceId());
            List<merchandise> savedMerchandise = null;
            if (request.merchandiseList() != null){
                savedMerchandise = merchandiseRequestToMerchandise(request);
            }
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
                    .merchandiseList(savedMerchandise)
                    .build();
            invoice saved = invoiceRepository.save(invoiceToSave);
            if (request.merchandiseList() != null){
                //savedMerchandise = merchandiseRequestToMerchandise(request);
                for (merchandise merch: savedMerchandise
                ) {
                    Optional<merchandise> toUpdate = merchandiseRepository.findById(merch.getMerchId());
                    toUpdate.get().setInvoice(saved);
                }
            }

            return saved;
        }
        else throw new requestException("Invoice already exist",HttpStatus.CONFLICT);
    }

    private void deleteInvoiceNumberByInvoiceNumber(String invoiceNumber) {
        Optional<invoiceNumber> invoiceN = invoiceNumberRepository.findById(invoiceNumber);
        if (invoiceN.isPresent()){
            invoiceNumberRepository.deleteById(invoiceNumber);
        }
    }


    public invoice updateInvoice(invoiceRequest request) {

        // update the invoice ID? if yes (merchandise should be updated also)
        // and the list of available ID should have a new record (the previous Invoice id)
        /////////////////////////////////////////
        Optional<invoice> toUpdate = invoiceRepository.findById(request.invoiceId());
        if(toUpdate.isPresent()){
            Optional<client> client = clientRepository.findById(request.clientId());
            toUpdate.get().setInvoiceDate(request.invoiceDate());
            toUpdate.get().setClient(client.get());
            toUpdate.get().setTotalTTC(request.totalTTC());
            toUpdate.get().setSpelledTotal(convertNumberToWords(request.totalTTC()));
            toUpdate.get().setTVA(request.totalTTC().doubleValue()/6);
            toUpdate.get().setPaymentMethod(paymentMethod.valueOf(request.paymentMethod()));
            toUpdate.get().setBankName(request.bankName());
            toUpdate.get().setCheckNumber(request.checkNumber());
            toUpdate.get().setPaymentDate(request.paymentDate());
            toUpdate.get().setPrinted(Boolean.valueOf(request.printed()));
            toUpdate.get().setInvoiceAction(action.valueOf(request.invoiceAction()));
            toUpdate.get().setInvoiceStatus(status.valueOf(request.invoiceStatus()));
            toUpdate.get().setInvoiceFile(request.invoiceFile());

            if(merchandiseRepository.findAllByInvoice_InvoiceId(request.invoiceId()) != null ){
                deleteAllMerchandiseUpdateInventory(request.invoiceId());
                List<merchandise> savedMerchandise = merchandiseRequestToMerchandise(request);
                for (merchandise merch: savedMerchandise
                ) {
                    Optional<merchandise> merchToUpdate = merchandiseRepository.findById(merch.getMerchId());
                    merchToUpdate.get().setInvoice(toUpdate.get());
                }
            }

            return invoiceRepository.save(toUpdate.get());
        }else throw new requestException("The invoice doesn't exist", HttpStatus.NOT_FOUND);
    }

    public void deleteAllMerchandiseUpdateInventory(String invoiceId){
        List<merchandise> merchandiseList = merchandiseRepository.findAllByInvoice_InvoiceId(invoiceId);
        if(merchandiseRepository.findAllByInvoice_InvoiceId(invoiceId) != null ){
            for (merchandise merch: merchandiseList
            ) {
                Optional<inventory> toUpdate = inventoryRepository.findByProductProductId(merch.getProduct().getProductId());
                Double availableQuantity = toUpdate.get().getAvailability();
                toUpdate.get().setAvailability(availableQuantity + merch.getQuantity());
            }
            merchandiseRepository.deleteByInvoice_InvoiceId(invoiceId);
        }
    }

    public void deleteInvoiceById(String invoiceId) {
        Optional<invoice> toDelete = invoiceRepository.findById(invoiceId);
        if(toDelete.isPresent()){
            deleteAllMerchandiseUpdateInventory(invoiceId);
            invoiceRepository.deleteById(invoiceId);
            invoiceNumberRepository.save(invoiceNumber.builder().invoiceNumber(invoiceId).build());
        }else throw new requestException("The invoice id you provided doesn't exist",HttpStatus.NOT_FOUND);
    }
}
