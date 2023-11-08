package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import com.onxshield.invoiceyou.invoicestatement.model.invoice;
import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.repository.clientRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.invoiceRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.inventoryRepository;
import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.request.merchandiseGenerationRequest;
import com.onxshield.invoiceyou.invoicestatement.request.merchandiseRequest;
import com.onxshield.invoiceyou.invoicestatement.response.basicInvoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.response.merchandiseGenerationResponse;
import com.onxshield.invoiceyou.invoicestatement.response.merchandiseResponse;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class invoiceService {
    private final clientRepository clientRepository_;
    private final invoiceRepository invoiceRepository;
    private final inventoryRepository inventoryRepository;
    public basicInvoiceResponse createBasicInvoice(basicInvoiceRequest request) {
        if(invoiceRepository.findById(request.getInvoiceId()).isPresent()){
            throw new requestException("The invoice already filled",
                    HttpStatus.CONFLICT);
        }else {
        var inv = invoice.builder()
                .invoiceId(request.getInvoiceId())
                .invoiceDate(request.getInvoiceDate())
                .client(clientRepository_.findById(request.getClientId()).orElseThrow())
                .totalTTC(request.getTotalTTC())
                .TVA(request.getTotalTTC()/6)
                .paymentMethod(request.getPaymentMethod())
                .spelledTotal(totalConverterToString(request.getTotalTTC()))
                .checkNumber(request.getCheckNumber() == null ? 0 : request.getCheckNumber()) //todo test it
                .merchandiseList(generatedMerch(request.getMerchandiseList()))
                .build();
        invoice toSave = invoiceRepository.save(inv);

        return basicInvoiceResponse.builder()
                .invoiceId(toSave.getInvoiceId())
                .invoiceDate(toSave.getInvoiceDate())
                .clientName(toSave.getClient().getName())
                .ICE(toSave.getClient().getICE())
                .totalTTC(toSave.getTotalTTC())
                .TVA(toSave.getTVA())
                .spelledTotal(toSave.getSpelledTotal())
                .paymentMethod(String.valueOf(toSave.getPaymentMethod()))
                .checkNumber(toSave.getCheckNumber())
                .merchandiseList((ArrayList<merchandiseResponse>) toSave.getMerchandiseList().stream().map(
                        merchandise -> new merchandiseResponse(
                                merchandise.getProduct().getName(),
                                merchandise.getProduct().getUnit().toString(),
                                merchandise.getQuantity(),
                                10d,
                                request.getTotalTTC()
                        )
                ).toList())
                .build();

        }
    }

    private List<merchandise> generatedMerch(ArrayList<merchandiseRequest> merchandiseList) {
        return null; //todo to be implemented
    }

    private String totalConverterToString(Double totalTTC) {
        return "spelled total"; //todo implement it later
    }


    private ArrayList<merchandiseGenerationResponse> invoiceIdGenerator(merchandiseGenerationRequest requestDetails) {

        ArrayList<merchandiseGenerationResponse> generatedMerch = new ArrayList<>();


        // to get a random index for the lists used below
        Random random = new Random();

        //todo
        //check the price level

        int generatedCount = 0;
        List<String> categories = Arrays.asList(requestDetails.getCategory().split(", "));
        double targetedTotal = 0d;
        //categorizing products by price range;
        // ranges of invoices
        //        // 1,000DH to 5,000DH
        //        // 5,000DH to 20,000DH
        //        // 20,000DH to +100,000DH
        // ranges of product prices pL(price level)
        // 0DH to 500DH
        // 500DH to 1,000DH
        // 1,000DH to 10,000DH
        double p1 = 0,p2 = 0,p3 = 0;
        // we should categorize the products based on: price range + categories in inputs
        // getting only products in the wanted category
        List<inventory> allProducts = inventoryRepository.findAll().stream().filter(
                inventory ->  inventory.getProduct().getCategoryList().contains(requestDetails.getCategory()
                )).toList();

        List<inventory> pL1 = allProducts.stream()
                .filter(inventory -> inventory.getSellPrice() <= p1 )
                .toList();
        List<inventory> pL2 = allProducts.stream()
                .filter(inventory -> inventory.getSellPrice() >= p1 & inventory.getSellPrice() <= p2)
                .toList();
        List<inventory> pL3 = allProducts.stream()
                .filter(inventory -> inventory.getSellPrice() <= p3 & inventory.getSellPrice() >= p1)
                .toList();



        if(requestDetails.getTotalTTC()<=5000){ //use pL1

            while(targetedTotal<=requestDetails.getTotalTTC()+1.5 & targetedTotal>=requestDetails.getTotalTTC()+1){



            }
        } else if (requestDetails.getTotalTTC()>=5000 & requestDetails.getTotalTTC()<=20000) { //use pL2

        }else { //use pL3

        }

        return generatedMerch; //todo implement it later
    }

    public static void main(String[] args) {


    }
}
