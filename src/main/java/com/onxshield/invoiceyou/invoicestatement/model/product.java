package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class product {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @NotBlank
    private String name;
    @NotBlank
    private unit unit;
    @NotBlank
    private List<category> categoryList;
}
