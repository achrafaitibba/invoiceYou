package com.onxshield.invoiceyou.bankstatement.service;

import com.onxshield.invoiceyou.bankstatement.dto.request.basicStatementRequest;
import com.onxshield.invoiceyou.bankstatement.model.statement;
import com.onxshield.invoiceyou.bankstatement.model.transactionType;
import com.onxshield.invoiceyou.bankstatement.repository.statementRepository;
import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class statementService {

    private final statementRepository statementRepository;
    public statement createBasicStatement(basicStatementRequest request) {

        return statementRepository.save(statement.builder()
                .statementDate(request.statementDate())
                .description(request.description())
                .transactionType(transactionType.valueOf(request.transactionType()))
                .amount(request.amount())
                .build());

    }

    public statement createStatement(statement statement) {
        return statementRepository.save(statement);
    }

    public statement updateStatement(statement statementRequest) {
        Optional<statement> toUpdate = statementRepository.findById(statementRequest.getStatementId());
        if(toUpdate.isPresent()){
            return statementRepository.save(statementRequest);
        }else throw  new requestException("The id provided doesn't exist", HttpStatus.NOT_FOUND);
    }
}
