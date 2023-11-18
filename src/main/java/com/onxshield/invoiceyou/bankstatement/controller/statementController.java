package com.onxshield.invoiceyou.bankstatement.controller;

import com.onxshield.invoiceyou.bankstatement.dto.request.basicStatementRequest;
import com.onxshield.invoiceyou.bankstatement.model.invoiceType;
import com.onxshield.invoiceyou.bankstatement.model.statement;
import com.onxshield.invoiceyou.bankstatement.model.status;
import com.onxshield.invoiceyou.bankstatement.model.transactionType;
import com.onxshield.invoiceyou.bankstatement.service.statementService;
import com.onxshield.invoiceyou.invoicestatement.model.action;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/invoiceType")
    public ResponseEntity<invoiceType[]> getInvoiceTypes(){
        return ResponseEntity.ok(invoiceType.values());
    }
    @PostMapping("/basic/create")
    public ResponseEntity<List<statement>> createBasicStatement(@RequestBody List<basicStatementRequest> request){
        return ResponseEntity.ok(statementService.createBasicStatement(request));
    }

    @PostMapping("/create")
    public ResponseEntity<statement> createStatement(@RequestBody statement statement){
        return ResponseEntity.ok(statementService.createStatement(statement));
    }

    @PutMapping("/update")
    public ResponseEntity<statement> updateStatement(@RequestBody statement statement){
        return ResponseEntity.ok(statementService.updateStatement(statement));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStatement(@PathVariable Long id){
        statementService.deleteStatement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<statement> getById(@PathVariable Long id){
        return ResponseEntity.ok(statementService.getById(id));
    }


    @RequestMapping(value = "/all/{page}/{size}", method = RequestMethod.GET)
    public Page<statement> getAllStatements(@PathVariable Integer page,
                                            @PathVariable Integer size){
        return statementService.getAllStatements(page, size);
    }
}
