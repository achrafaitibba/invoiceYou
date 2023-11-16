package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface merchandiseRepository extends JpaRepository<merchandise, Long> {


    //int deleteByInvoice_InvoiceId(String invoiceId);
}
