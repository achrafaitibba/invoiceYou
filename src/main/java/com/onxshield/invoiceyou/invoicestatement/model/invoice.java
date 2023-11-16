package com.onxshield.invoiceyou.invoicestatement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client_id", referencedColumnName = "client_id")
    @NotNull
    private client client;

    @NotNull
    private Long totalTTC;

    @NotNull
    private String spelledTotal;

    private Double TVA; 

    @NotNull
    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    private String bankName;

    private Double discount; //todo, return it with the suggestion algorithm

    private Integer checkNumber;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @OneToMany
    private List<merchandise> merchandiseList;

    private Boolean printed = false;

    @Enumerated(EnumType.STRING)
    private action invoiceAction = action.NOT_YET;

    //@Builder.Default //todo , check how this annotation work in docs
    @Enumerated(EnumType.STRING)
    private status invoiceStatus = status.NOT_YET;

    private String invoiceFile = ""; //todo file could be word or pdf
}
