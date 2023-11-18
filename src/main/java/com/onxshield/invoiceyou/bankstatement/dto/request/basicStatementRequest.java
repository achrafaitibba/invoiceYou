package com.onxshield.invoiceyou.bankstatement.dto.request;

import java.util.Date;

public record basicStatementRequest(
        Date statementDate,
        String description,
        String transactionType,
        Long totalTTC




) {
}
