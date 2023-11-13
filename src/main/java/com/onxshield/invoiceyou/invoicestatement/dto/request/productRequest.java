package com.onxshield.invoiceyou.invoicestatement.dto.request;

public record productRequest(

        String name,
        String unit,
        String[] categories
) {
}
