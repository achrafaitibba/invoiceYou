package com.onxshield.invoiceyou.invoicestatement.dto.request;

import java.util.Date;
import java.util.List;

public record invoiceRequest<T>(

        String invoiceId,
        Date invoiceDate,
        Long clientId,
        Long totalTTC,
        String paymentMethod,
        String bankName,
        Integer checkNumber,
        Date paymentDate,
        List<T> merchandiseList,
        String printed,
        String invoiceAction,
        String invoiceStatus,
        String invoiceFile


) {
}
