package com.onxshield.invoiceyou.invoicestatement.dto.request;

public record merchandiseDetailsRequest(
        Long merchId,
        Long productId,
        Double quantity,
        Double total
) {
}
