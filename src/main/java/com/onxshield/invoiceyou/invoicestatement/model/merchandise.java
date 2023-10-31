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
    private Long merchId;

    @NotNull
    @OneToOne
    private product product;
    private Long quantity;
    private Long total; //todo product_sell price * quantity

}
