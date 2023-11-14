package com.onxshield.invoiceyou.invoicestatement.dto.response;

public record merchandiseResponse(

        String productName,
        String unit,
        Double quantity,
        Double sellPrice,
        Long total


) {
}
