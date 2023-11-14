package com.onxshield.invoiceyou.invoicestatement.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;

public record basicInvoiceRequest(

        Long clientId,
        Date invoiceDate,
        String invoiceId,
        String paymentMethod,
        Integer checkNumber,
        List<merchandiseRequest> merchandiseList,
        Double discount

) {
}
