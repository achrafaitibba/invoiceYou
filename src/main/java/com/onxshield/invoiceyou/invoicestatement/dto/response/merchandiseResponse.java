package com.onxshield.invoiceyou.invoicestatement.dto.response;

public record merchandiseResponse(
        Long merchId,
        String productName,
        String unit,
        Double quantity,
        Double sellPrice,
        Long total


) {
}
