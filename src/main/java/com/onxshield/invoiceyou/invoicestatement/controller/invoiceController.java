package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.dto.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.model.action;
import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
import com.onxshield.invoiceyou.invoicestatement.model.status;
import com.onxshield.invoiceyou.invoicestatement.service.invoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class invoiceController {

    private final invoiceService invoiceService;
    @GetMapping("/status")
    public ResponseEntity<status[]> allStatus() {
        return ResponseEntity.ok(status.values());
    }

    @GetMapping("/payMethods")
    public ResponseEntity<paymentMethod[]> paymentMethods() {
        return ResponseEntity.ok(paymentMethod.values());
    }

    @GetMapping("/actions")
    public ResponseEntity<action[]> actions() {
        return ResponseEntity.ok(action.values());
    }

//    @PostMapping("/basic/create")
//    public ResponseEntity<basicInvoiceResponse> createBasicInvoice(@RequestBody basicInvoiceRequest request) {
//        return ResponseEntity.ok(invoiceService.createBasicInvoice());
//
//    }
}