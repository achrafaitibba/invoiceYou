package com.onxshield.invoiceyou.invoicestatement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private Long productId;
    @NotNull
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private unit unit;
    @NotNull
    private String categoryList;
}
