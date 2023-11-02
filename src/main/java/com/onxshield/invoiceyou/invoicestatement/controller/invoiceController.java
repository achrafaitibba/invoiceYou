package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.request.basicInvoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.response.invoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.service.invoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class invoiceController {
    private final invoiceService invoiceService;
    @PostMapping("/create/basic")
    public ResponseEntity<invoiceResponse> createBasicInvoice(
            @RequestBody basicInvoiceRequest request
            ){
        return ResponseEntity.ok(invoiceService.createBasicInvoice(request));
    }
    @PostMapping("/create")
    public void createInvoice(
    ){

    }
    @PostMapping("/update")
    public void updateInvoice(){

    }
    @GetMapping("/all")
    public void getAllInvoices(){

    }

    @GetMapping("/{id}")
    public void getInvoiceById(@PathVariable String id){
        
    }


}
