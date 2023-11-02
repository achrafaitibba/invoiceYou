package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.response.invoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.service.invoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class invoiceController {
    private final invoiceService invoiceService;
    @PostMapping("/basic/create")
    public ResponseEntity<invoiceResponse> createInvoice(
            @RequestBody basicInvoiceRequest request
            ){
        return ResponseEntity.ok(invoiceService.createBasicInvoice(request));
    }
}
