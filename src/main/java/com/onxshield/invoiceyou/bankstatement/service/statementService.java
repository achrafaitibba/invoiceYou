package com.onxshield.invoiceyou.bankstatement.service;

import com.onxshield.invoiceyou.bankstatement.dto.request.basicStatementRequest;
import com.onxshield.invoiceyou.bankstatement.model.statement;
import com.onxshield.invoiceyou.bankstatement.model.transactionType;
import com.onxshield.invoiceyou.bankstatement.repository.statementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class statementService {

    private final statementRepository statementRepository;
    public statement createBasicStatement(basicStatementRequest request) {

        return statementRepository.save(statement.builder()
                .statementDate(request.statementDate())
                .description(request.description())
                .transactionType(transactionType.valueOf(request.transactionType()))
                .totalTTC(request.totalTTC())
                .build());

    }
}
