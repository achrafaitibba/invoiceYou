package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.model.status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class invoiceController {

    @GetMapping("/status")
    public ResponseEntity<status[]> allStatus(){
        return ResponseEntity.ok(status.values());
    }
}
