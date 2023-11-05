package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.repository.inventoryRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.productRepository;
import com.onxshield.invoiceyou.invoicestatement.request.productRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class inventoryService {
    private final productRepository productRepository;
    private final inventoryRepository inventoryRepository;
    public product addProduct(productRequest request) {
        var savedProduct = productRepository.save(
                product.builder()
                        .name(request.getName())
                        .unit(request.getUnit())
                        .categoryList(String.join(", ", request.getCategoryList()))
                        .build()
        );
        inventoryRepository.save(inventory.builder()
                        .product(savedProduct)
                        .availability(0)
                        .sellPrice(0)
                        .buyPrice(0)
                .build());
        return savedProduct;
    }

    public int deleteProduct(Long id) {
        var pro = productRepository.findById(id).orElseThrow();
        var inv = inventoryRepository.findByProductProductId(id).orElseThrow();
        inventoryRepository.deleteById(id);
        productRepository.deleteById(id);
        return 1;
    }
}
