package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.invoiceNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface invoiceNumberRepository extends JpaRepository<invoiceNumber, String> {
}
