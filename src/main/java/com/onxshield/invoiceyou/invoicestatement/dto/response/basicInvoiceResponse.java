package com.onxshield.invoiceyou.invoicestatement.dto.response;

import java.util.Date;
import java.util.List;

public record basicInvoiceResponse(

        String clientName,
        String invoiceNumber,
        Long ICE,
        Date invoiceDate,
        List<merchandiseResponse> merchandiseList,
        Long totalTTC,
        Double TVA,
        String spelledTotal,
        String paymentMethod,
        Integer checkNumber


) {
}
