package com.onxshield.invoiceyou.invoicestatement.dto.request;

import java.util.Date;

public record invoiceRequest(

        String invoiceId,
        Date invoiceDate,
        Long clientId,
        Long totalTTC,
        String paymentMethod,
        String bankName,
        Integer checkNumber,
        Date paymentDate

) {
}
