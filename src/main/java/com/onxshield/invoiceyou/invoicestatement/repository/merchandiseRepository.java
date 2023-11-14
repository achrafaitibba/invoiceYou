package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface merchandiseRepository extends JpaRepository<merchandise, Long> {
}
