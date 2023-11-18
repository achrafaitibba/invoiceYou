package com.onxshield.invoiceyou.bankstatement.model;

import com.onxshield.invoiceyou.invoicestatement.model.action;
import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.management.DescriptorKey;
import java.util.Date;

@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class statement {
    @Id
    @GeneratedValue
    @Column(name = "statement_id")
    private Integer statementId;

    @Column(name = "statement_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date statementDate;

    @NotNull
    private String description;

    private transactionType transactionType;

    private Long totalTTC;
    ///////////////////////////////////////////////
    private String partnerName;

    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    private Integer checkNumber;

    private status status;

    private Boolean printed;

    private String file;

    private action action;

    private invoiceType invoiceType;

    private String invoiceNumber;

    @Column(name = "invoice_date")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
}
