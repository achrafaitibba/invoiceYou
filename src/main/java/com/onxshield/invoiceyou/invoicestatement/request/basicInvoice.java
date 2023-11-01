package com.onxshield.invoiceyou.invoicestatement.request;

import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;

import java.util.Date;
import java.util.List;

public record basicInvoice(
        String invoiceId,
        Date invoiceDate,
        String clientName,
        String ICE,
        List<merchandise> merchandiseList,
        Long totalTTC,
        paymentMethod paymentMethode
) {
}
