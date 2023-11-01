package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.request.basicInvoice;
import com.onxshield.invoiceyou.invoicestatement.response.invoiceResponse;
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

    @PostMapping("/basic/create")
    public ResponseEntity<invoiceResponse> createInvoice(
            @RequestBody basicInvoice request
            ){
        return ResponseEntity.ok(invoiceService.createBasicInvoice(request));
    }
}
