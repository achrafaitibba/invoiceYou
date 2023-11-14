package com.onxshield.invoiceyou.invoicestatement.dto.response;

import java.util.Date;

public record invoiceResponse(

        String invoiceId,
        Date invoiceDate,
        String clientName,
        Long ICE,
        Long totalTTC,
        String spelledTotal,
        Double TVA





) {
}
