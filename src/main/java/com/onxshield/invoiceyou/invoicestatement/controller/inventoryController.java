package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.request.inventoryRequest;
import com.onxshield.invoiceyou.invoicestatement.request.productRequest;
import com.onxshield.invoiceyou.invoicestatement.service.inventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class inventoryController {

    private final inventoryService inventoryService_;
    // increase/decrease : product items

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody productRequest request){ //todo make it better
        return ResponseEntity.ok(inventoryService_.addProduct(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable Long id ){
        return ResponseEntity.ok(inventoryService_.deleteProduct(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Integer> updateProduct(@PathVariable Long id, @RequestBody productRequest request){
        return ResponseEntity.ok(inventoryService_.updateProduct(id, request));
    }
    @PatchMapping("/inventory/update/{productId}")
    public ResponseEntity<Integer> updateInventory(@PathVariable Long productId, @RequestBody inventoryRequest request){
        return ResponseEntity.ok(inventoryService_.updateInventory(productId, request));
    }

}
