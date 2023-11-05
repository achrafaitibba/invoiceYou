package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface clientRepository extends JpaRepository<client, Long> {
}
