package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    @JoinColumn(name = "fk_product_id", referencedColumnName = "product_id")
    private product product;
    private long availability;
    private long buyPrice;
    private long sellPrice;
}
