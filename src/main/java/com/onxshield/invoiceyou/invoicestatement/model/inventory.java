package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.*;
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
    private product product;
    private Double availability = 0D;
    private Double buyPrice = 0D;
    private Double sellPrice = 0D;

}
