package com.onxshield.invoiceyou.bankstatement.service;

import com.onxshield.invoiceyou.bankstatement.dto.request.basicStatementRequest;
import com.onxshield.invoiceyou.bankstatement.model.statement;
import com.onxshield.invoiceyou.bankstatement.model.transactionType;
import com.onxshield.invoiceyou.bankstatement.repository.statementRepository;
import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class statementService {

    private final statementRepository statementRepository;
    public List<statement> createBasicStatements(List<basicStatementRequest> request) {
        List<statement> saved = new ArrayList<>();
        for (basicStatementRequest req: request
             ) {
           saved.add(statementRepository.save(statement.builder()
                    .statementDate(req.statementDate())
                    .description(req.description())
                    .transactionType(transactionType.valueOf(req.transactionType()))
                    .amount(req.amount())
                    .build()));
        }
        return saved;
    }
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

    public void deleteStatement(Long id) {
        Optional<statement> toDelete = statementRepository.findById(id);
        if(toDelete.isPresent()){
            statementRepository.deleteById(id);
        }else throw new requestException("The id you provided doesn't exist", HttpStatus.NOT_FOUND);
    }

    public statement getById(Long id) {
        Optional<statement> statement = statementRepository.findById(id);
        if(statement.isPresent()){
           return statement.get();
        }else throw new requestException("The id you provided doesn't exist", HttpStatus.NOT_FOUND);
    }

    public Page<statement> getAllStatements(Integer pageNumber, Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return statementRepository.findAll(pageable);
    }
}
