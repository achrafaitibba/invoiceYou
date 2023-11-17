package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface merchandiseRepository extends JpaRepository<merchandise, Long> {


    void deleteByInvoice_InvoiceId(String id);
    void deleteAllByInvoice_InvoiceId(String id);
    List<Optional<merchandise>> findAllByInvoice_InvoiceId(String id);
}
