package com.onxshield.invoiceyou.invoicestatement.service;


import com.onxshield.invoiceyou.invoicestatement.dto.request.basicInvoiceRequest;
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
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


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
}
