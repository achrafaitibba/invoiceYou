package com.onxshield.invoiceyou.invoicestatement.dto.request;

public record detailedMerchandiseRequest(

        Long merchId,
        Long productId,
        Double quantity,
        Double total

) {
}
