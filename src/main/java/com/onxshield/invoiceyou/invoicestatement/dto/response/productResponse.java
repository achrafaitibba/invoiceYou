package com.onxshield.invoiceyou.invoicestatement.dto.response;

public record productResponse(

        Long productId,
        String name,
        String unit,
        String categoryList
) {
}
