package com.onxshield.invoiceyou.bankstatement.repository;

import com.onxshield.invoiceyou.bankstatement.model.statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface statementRepository extends JpaRepository<statement, Long> {
}
