package com.onxshield.invoiceyou.invoicestatement.response;

import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;

import java.util.Date;

public record invoiceResponse(
        String invoiceId,
        Date invoiceDate,
        String clientName,
        Long totalTTC,
        paymentMethod paymentMethode,
        String bankName

) {
}
