package com.onxshield.invoiceyou.invoicestatement.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private Double total;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    //@JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "invoiceId")
    @JsonIdentityReference(alwaysAsId = true)
    private invoice invoice;



}
