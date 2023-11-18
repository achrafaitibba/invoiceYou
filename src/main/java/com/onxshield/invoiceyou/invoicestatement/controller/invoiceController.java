package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.dto.request.invoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.basicInvoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.model.action;
import com.onxshield.invoiceyou.invoicestatement.model.invoice;
import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
import com.onxshield.invoiceyou.invoicestatement.model.status;
import com.onxshield.invoiceyou.invoicestatement.service.invoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/convertNumber/{total}")
    public ResponseEntity<String> convertNumberToWords(@PathVariable Long total){
        return ResponseEntity.ok(invoiceService.convertNumberToWords(total));
    }


    //todo pagination
    @GetMapping("/availableInvoiceNumbers")
    public ResponseEntity<String[]> getAvailableInvoiceNumbers(){
        return ResponseEntity.ok(invoiceService.getAvailableInvoiceNumbers());
    }

    @GetMapping("/generateInvoiceNumber")
    public ResponseEntity<String> generateNewInvoiceNumber(){
        return ResponseEntity.ok(invoiceService.generateInvoiceNumber());
    }

    @GetMapping("/TVA/calculate/{total}")
    public ResponseEntity<Double> calculateTVA(@PathVariable Long total){
        return ResponseEntity.ok(total.doubleValue()/6);
    }


    @GetMapping("/basic/{invoiceId}")
    public ResponseEntity<basicInvoiceResponse> getBasicInvoiceById(@PathVariable String invoiceId){
        return ResponseEntity.ok(invoiceService.getBasicInvoiceById(invoiceId));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<invoice> getInvoiceById(@PathVariable String invoiceId){
        return ResponseEntity.ok(invoiceService.getInvoiceById(invoiceId));
    }


    //todo pagination, sorting by date/total
    @GetMapping("/all")
    public ResponseEntity<List<invoice>> getAllInvoices(){
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @PostMapping("/basic/create")
    public ResponseEntity<invoice> createBasicInvoice(@RequestBody invoiceRequest request) {
        return ResponseEntity.ok(invoiceService.createBasicInvoice(request));
    }
    @PostMapping("/create")
    public ResponseEntity<invoice> createInvoice(@RequestBody invoiceRequest request) {
        return ResponseEntity.ok(invoiceService.createInvoice(request));
    }

    @PutMapping("/update")
    public ResponseEntity<invoice> updateInvoice(@RequestBody invoiceRequest request){
        return ResponseEntity.ok(invoiceService.updateInvoice(request));
    }


    @DeleteMapping("/delete/{invoiceId}")
    public ResponseEntity<Void> deleteInvoiceById(@PathVariable String invoiceId) {
        invoiceService.deleteInvoiceById(invoiceId);
        return ResponseEntity.noContent().build();
    }




}