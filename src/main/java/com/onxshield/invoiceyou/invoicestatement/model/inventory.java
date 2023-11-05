package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class inventory {
    @Id
    @GeneratedValue
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "fk_product_id", referencedColumnName = "product_id", unique = true)
    private product product;
    private Long availability;
    private Long buyPrice;
    private Long sellPrice;
}
