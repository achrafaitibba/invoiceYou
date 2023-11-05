package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.model.invoice;
import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import com.onxshield.invoiceyou.invoicestatement.repository.clientRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.invoiceRepository;
import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.request.merchandiseRequest;
import com.onxshield.invoiceyou.invoicestatement.response.basicInvoiceResponse;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        invoiceRepository.save(inv);

        return null;
    }

    private List<merchandise> generatedMerch(ArrayList<merchandiseRequest> merchandiseList) {
        return null; //todo to be implemented
    }

    private String totalConverterToString(Long totalTTC) {
        return "spelled total"; //todo implement it later
    }


    private String invoiceIdGenerator() {
        return "testId"; //todo implement it later
    }
}
