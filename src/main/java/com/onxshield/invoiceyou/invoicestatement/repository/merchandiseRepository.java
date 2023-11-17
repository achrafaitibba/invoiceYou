package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface merchandiseRepository extends JpaRepository<merchandise, Long> {


    void deleteByInvoice_InvoiceId(String id);
    List<merchandise> findAllByInvoice_InvoiceId(String id);
}
