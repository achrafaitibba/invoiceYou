package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.repository.inventoryRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.productRepository;
import com.onxshield.invoiceyou.invoicestatement.request.productRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        //todo make this code better, change the response entity answer in the controller
        productRepository.findById(id).orElseThrow();
        inventoryRepository.findByProductProductId(id).orElseThrow();
        inventoryRepository.deleteById(id);
        productRepository.deleteById(id);
        return 1;
    }

    public Integer updateProduct(Long id, productRequest request) {
        Optional<product> productToUpdate = productRepository.findById(id);
        if(productToUpdate.isPresent()){
            productToUpdate.get().setName(request.getName());
            productToUpdate.get().setUnit(request.getUnit());
            productToUpdate.get().setCategoryList(String.join(", ", request.getCategoryList()));
            productRepository.save(productToUpdate.get());
            return 1;
        }
        else return 0;
    }
}
