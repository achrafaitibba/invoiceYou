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
public class merchandise {

    @Id
    @Column(name = "merch_id")
    @GeneratedValue
    private Long merchId;

    @ManyToOne
    @NotNull
    private product product;

    @NotNull
    private Double quantity;

    @NotNull
    private Double total; //todo product_sell price * quantity

}
