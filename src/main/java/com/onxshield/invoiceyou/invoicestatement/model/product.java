package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Long productId;
    private String name;
    private unit unit;
    private List<category> categoryList;
}
