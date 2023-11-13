package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.dto.request.productRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.productResponse;
import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.model.unit;
import com.onxshield.invoiceyou.invoicestatement.repository.inventoryRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.productRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class inventoryService {

    private final productRepository productRepository;
    private final inventoryRepository inventoryRepository;
    public productResponse createProduct(productRequest request) {
        product productToSave = productRepository.save(
                product.builder()
                        .name(request.name())
                        .unit(unit.valueOf(request.unit()))
                        .categoryList(
                                Arrays.toString(request.categories())
                        )
                        .build()
        );
        inventory inventory = new inventory();
        inventory.setProduct(productToSave);
        inventory.setInventoryId(productToSave.getProductId());
        inventoryRepository.save(inventory);
        return new productResponse(
                productToSave.getProductId(),
                productToSave.getName(),
                productToSave.getUnit().toString(),
                productToSave.getCategoryList()
        );
    }


}
