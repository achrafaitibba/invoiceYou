package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "invoice_statement")
public class invoice {

    @Id
    @Column(name = "invoice_id")
    private String invoiceId;

    @Column(name = "invoice_date")
    private Date invoiceDAte;

    //todo this is just for the mapping, review the attributes to select what to send to the client side
    @OneToOne
    @JoinColumn(name = "fk_client_id", referencedColumnName = "client_id")
    private client client; //todo extract ICE, NAME, BANK NAME

    @NotBlank
    private Long totalTTC; //todo , convert it to text format "5=> five.."

    @NotBlank
    private String spelledTotal;

    @NotBlank
    private Long TVA;

    @NotBlank
    private paymentMethod paymentMethod;

    @NotBlank
    private String bankName;

    @NotBlank
    private Integer checkNumber;

    @NotBlank
    private Date paymentDate;

    @OneToMany
    //@JoinColumn(name = "fk_merch_id", referencedColumnName = "merch_id")
    @NotBlank
    private List<merchandise> merchandiseList;

    @NotBlank
    private Boolean printed;

    @NotBlank
    private action action;

    @NotBlank
    private status status;

    @NotBlank
    private String invoiceFile; //todo file could be word or pdf
}
