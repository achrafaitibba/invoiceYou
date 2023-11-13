package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.model.category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class inventoryController {

    @GetMapping("/categories")
    public ResponseEntity<category[]> getAllCategories(){
        return ResponseEntity.ok(category.values());
    }
}
