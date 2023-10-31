package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_statement")
public class invoice {

    @Id
    @Column(name = "invoice_id")
    @NotNull
    private String invoiceId;

    @Column(name = "invoice_date")
    private Date invoiceDAte;

    //todo this is just for the mapping, review the attributes to select what to send to the client side
    @OneToOne
    @JoinColumn(name = "fk_client_id", referencedColumnName = "client_id")
    @NotNull
    private client client; //todo extract ICE, NAME, BANK NAME

    @NotBlank
    private Long totalTTC;

    @NotBlank
    private Long TVA;

    @NotBlank
    private paymentMethod paymentMethod;

    @NotBlank
    private int checkNumber;

    @NotBlank
    private Date paymentDate;

    @OneToMany
    @JoinColumn(name = "fk_merch_id", referencedColumnName = "merch_id")
    @NotBlank
    private List<merchandise> merchandiseList;

    @NotBlank
    private Boolean printed;

    @NotBlank
    private action action;

    @NotBlank
    private status status;

    @NotBlank
    private String invoiceFile; //file could be word or pdf
}
