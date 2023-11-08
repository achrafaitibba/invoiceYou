package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.model.client;
import com.onxshield.invoiceyou.invoicestatement.request.clientRequest;
import com.onxshield.invoiceyou.invoicestatement.service.clientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class clientController {

    private final clientService clientService_;

    @PostMapping("/add")
    public ResponseEntity<client> addClient(@RequestBody clientRequest request){
        return ResponseEntity.ok(clientService_.addClient(request));
    }

}
