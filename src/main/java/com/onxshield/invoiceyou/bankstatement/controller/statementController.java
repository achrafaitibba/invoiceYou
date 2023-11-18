package com.onxshield.invoiceyou.bankstatement.controller;

import com.onxshield.invoiceyou.bankstatement.model.invoiceType;
import com.onxshield.invoiceyou.bankstatement.model.status;
import com.onxshield.invoiceyou.bankstatement.model.transactionType;
import com.onxshield.invoiceyou.bankstatement.service.statementService;
import com.onxshield.invoiceyou.invoicestatement.model.action;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statements")
public class statementController {

    private final statementService statementService;


    @GetMapping("/transactionType")
    public ResponseEntity<transactionType[]> getTransactionTypes(){
        return ResponseEntity.ok(transactionType.values());
    }

    @GetMapping("/status")
    public ResponseEntity<status[]> getStatus(){
        return ResponseEntity.ok(status.values());
    }
    @GetMapping("/action")
    public ResponseEntity<action[]> getActions(){
        return ResponseEntity.ok(action.values());
    }
   
}
