package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class invoiceNumber {
    @Id
    @Column(name = "invoice_number")
    private String invoiceNumber;
}
