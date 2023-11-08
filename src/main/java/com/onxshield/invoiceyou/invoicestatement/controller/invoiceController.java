package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.response.basicInvoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.service.invoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class invoiceController {
    private final invoiceService invoiceServices;
    @PostMapping("/add/basic")
    public ResponseEntity<basicInvoiceResponse> createBasicInvoice(@RequestBody basicInvoiceRequest request){
        return ResponseEntity.ok(invoiceServices.createBasicInvoice(request));
    }
}
