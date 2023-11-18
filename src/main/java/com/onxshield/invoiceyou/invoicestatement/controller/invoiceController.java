package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.dto.request.invoiceRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.basicInvoiceResponse;
import com.onxshield.invoiceyou.invoicestatement.model.action;
import com.onxshield.invoiceyou.invoicestatement.model.invoice;
import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
import com.onxshield.invoiceyou.invoicestatement.model.status;
import com.onxshield.invoiceyou.invoicestatement.service.invoiceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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


    @GetMapping("/basic/")
    public ResponseEntity<basicInvoiceResponse> getBasicInvoiceById(@RequestParam String invoiceId){
        return ResponseEntity.ok(invoiceService.getBasicInvoiceById(invoiceId));
    }

    @GetMapping("/")
    public ResponseEntity<invoice> getInvoiceById(@RequestParam String invoiceId){
        return ResponseEntity.ok(invoiceService.getInvoiceById(invoiceId));
    }


    @RequestMapping(value = "/all/{page}/{size}/{direction}/{sortBy}", method = RequestMethod.GET)
    public Page<invoice> getAllInvoices(@PathVariable Integer page,
                                              @PathVariable Integer size,
                                              @PathVariable String direction,
                                              @PathVariable String sortBy){
        return invoiceService.getAllInvoices(page, size, direction, sortBy);
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


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteInvoiceById(@RequestParam String invoiceId) {
        invoiceService.deleteInvoiceById(invoiceId);
        return ResponseEntity.noContent().build();
    }




}