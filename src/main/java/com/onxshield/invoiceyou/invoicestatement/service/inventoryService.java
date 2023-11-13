package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.dto.request.productRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.productResponse;
import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import com.onxshield.invoiceyou.invoicestatement.model.product;
import com.onxshield.invoiceyou.invoicestatement.model.unit;
import com.onxshield.invoiceyou.invoicestatement.repository.inventoryRepository;
import com.onxshield.invoiceyou.invoicestatement.repository.productRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
        inventoryRepository.save(inventory);
        return new productResponse(
                productToSave.getProductId(),
                productToSave.getName(),
                productToSave.getUnit().toString(),
                productToSave.getCategoryList()
        );
    }


    public List<productResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new productResponse(
                        product.getProductId(),
                        product.getName(),
                        product.getUnit().toString(),
                        product.getCategoryList()
                )).toList();
    }

    public productResponse getProductByIdc(Long id) {
        Optional<product> toFind = productRepository.findById(id);
        if(toFind.isPresent()){
            return new productResponse(
                    toFind.get().getProductId(),
                    toFind.get().getName(),
                    toFind.get().getUnit().toString(),
                    toFind.get().getCategoryList()

            );
        }else {
            throw new requestException("The id you provided doesn't exist.", HttpStatus.NOT_FOUND);        }

    }

    public productResponse updateProduct(Long id, productRequest request) {
        Optional<product> toUpdate = productRepository.findById(id);
        if(toUpdate.isPresent()){
            toUpdate.get().setName(request.name());
            toUpdate.get().setUnit(unit.valueOf(request.unit()));
            toUpdate.get().setCategoryList(Arrays.toString(request.categories()));
            productRepository.save(toUpdate.get());
            return new productResponse(
                    id,
                    toUpdate.get().getName(),
                    toUpdate.get().getUnit().toString(),
                    toUpdate.get().getCategoryList()

            );
        }else throw new requestException("The id you provided doesn't exist.", HttpStatus.NOT_FOUND);
    }
}
