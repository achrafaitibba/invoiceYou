package com.onxshield.invoiceyou.invoicestatement.dto.request;

import java.util.Date;

public record basicInvoiceRequest(

        Long clientId,
        Date invoiceDate,
        String invoiceId

) {
}
