package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.model.client;
import com.onxshield.invoiceyou.invoicestatement.model.invoice;
import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.repository.clientRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.invoiceRepository;
import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.request.merchandiseRequest;
import com.onxshield.invoiceyou.invoicestatement.response.basicInvoiceResponse;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class invoiceService {
    private final clientRepository clientRepository_;
    private final invoiceRepository invoiceRepository;
    public basicInvoiceResponse createBasicInvoice(basicInvoiceRequest request) {
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
        var savedInvoice = invoiceRepository.save(inv);

        return null;
    }

    private List<merchandise> generatedMerch(ArrayList<merchandiseRequest> merchandiseList) {
        //the following code is just for testing without inserting into db directly
        List<merchandise> merch = new ArrayList<>();
        merchandise m1 = new merchandise();

        product p1 = new product();
        p1.setName("p1");
        p1.setProductId(1L);
        product p2 = new product();
        p2.setName("p2");
        p2.setProductId(2L);

        m1.setQuantity(10L);
        m1.setTotal(m1.getQuantity());


        //end of test code
        return null; //todo to be implemented
    }

    private String totalConverterToString(Long totalTTC) {
        return "spelled total"; //todo implement it later
    }


    private String invoiceIdGenerator() {
        return "testId"; //todo implement it later
    }
}
