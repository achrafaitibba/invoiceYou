package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.dto.request.clientRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.clientResponse;
import com.onxshield.invoiceyou.invoicestatement.service.clientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class clientController {
    private final clientService clientService;
    @PostMapping("/create")
    public ResponseEntity<clientResponse> createClient(@RequestBody clientRequest request){
        return ResponseEntity.ok(clientService.createClient(request));
    }



}
