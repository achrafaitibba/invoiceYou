package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.repository.iInvoiceRepository;
import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.response.invoiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class invoiceService {
    private final iInvoiceRepository invoiceRepository;
    public invoiceResponse createBasicInvoice(basicInvoiceRequest request) {
        return invoiceResponse.builder().build();
    }
}
