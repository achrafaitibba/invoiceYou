package com.onxshield.invoiceyou.invoicestatement.repository;

import com.onxshield.invoiceyou.invoicestatement.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface inventoryRepository extends JpaRepository<inventory, Long> {

}
