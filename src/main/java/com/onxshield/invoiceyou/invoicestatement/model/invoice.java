package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
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
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client_id", referencedColumnName = "client_id")
    @NotNull
    private client client;

    @NotNull
    private Long totalTTC;

    @NotNull
    private String spelledTotal; //todo live conversion endpoint

    private Long TVA; //todo live conversion endpoint

    @NotNull
    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    private String bankName;

    private Integer checkNumber; //todo could be null if paymentMethod = VIR/ESP

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @OneToMany
    //@JoinColumn(name = "fk_merch_id", referencedColumnName = "merch_id")
    //@NotBlank
    private List<merchandise> merchandiseList;

    @Builder.Default
    private Boolean printed = false;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private action invoiceAction = action.NOT_YET;

    @Builder.Default //todo , check how this annotation work in docs
    @Enumerated(EnumType.STRING)
    private status invoiceStatus = status.NOT_YET;

    private String invoiceFile = ""; //todo file could be word or pdf
}
