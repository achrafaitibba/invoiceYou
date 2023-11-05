package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.request.productRequest;
import com.onxshield.invoiceyou.invoicestatement.service.inventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class inventoryController {

    private final inventoryService inventoryService_;

    // delete/update : product details
    // increase/decrease : product items

    @PostMapping("/product/add")
    public ResponseEntity addProduct(@RequestBody productRequest request){
        return ResponseEntity.ok(inventoryService_.addProduct(request));
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable Long id ){
        return ResponseEntity.ok(inventoryService_.deleteProduct(id));
    }
}
