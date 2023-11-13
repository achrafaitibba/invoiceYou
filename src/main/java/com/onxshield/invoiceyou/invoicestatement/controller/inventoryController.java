package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.dto.request.inventoryRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.request.productRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.inventoryResponse;
import com.onxshield.invoiceyou.invoicestatement.dto.response.productResponse;
import com.onxshield.invoiceyou.invoicestatement.model.category;
import com.onxshield.invoiceyou.invoicestatement.model.unit;
import com.onxshield.invoiceyou.invoicestatement.service.inventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class inventoryController {

    private final inventoryService inventoryService;

    @GetMapping("/products/categories")
    public ResponseEntity<category[]> getAllCategories(){
        return ResponseEntity.ok(category.values());
    }
    @GetMapping("/products/units")
    public ResponseEntity<unit[]> getAllUnits(){
        return ResponseEntity.ok(unit.values());
    }

    @GetMapping("/products")
    public ResponseEntity<List<productResponse>> getAllProducts(){
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }



    @PostMapping("/products/add")
    public ResponseEntity<productResponse> createProduct(@RequestBody productRequest request){
        return ResponseEntity.ok(inventoryService.createProduct(request));
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<productResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(inventoryService.getProductById(id));

    }

    @PutMapping("/products/update/{id}")
    public ResponseEntity<productResponse> updateProduct(@PathVariable Long id,
            @RequestBody productRequest request){

        return ResponseEntity.ok(inventoryService.updateProduct(id, request));
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Integer> updateProduct(@PathVariable Long id){

        return ResponseEntity.ok(inventoryService.deleteProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<inventoryResponse>> getInventory(){
        return ResponseEntity.ok(inventoryService.getInventory());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<inventoryResponse> updateProductInventory(@PathVariable Long productId,
                                                                    @RequestBody inventoryRequest request){
        return ResponseEntity.ok(inventoryService.updateProductInventory(productId, request));
    }
}
