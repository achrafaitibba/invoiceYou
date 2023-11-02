package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iInvoiceRepository extends JpaRepository<invoice, String> {
}
