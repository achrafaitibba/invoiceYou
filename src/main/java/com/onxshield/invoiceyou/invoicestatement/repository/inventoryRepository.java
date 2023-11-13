package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inventoryRepository extends JpaRepository<inventory, Long> {

    void deleteByProductProductId(Long id);
}
