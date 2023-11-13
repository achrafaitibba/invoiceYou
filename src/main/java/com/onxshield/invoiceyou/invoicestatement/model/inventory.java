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
public class inventory {
    @Id
    @Column(name = "fk_product_id")
    private Long inventoryId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "product_id", unique = true)
    private product product;
    private Double availability = 0D;
    private Double buyPrice = 0D;
    private Double sellPrice = 0D;

}
