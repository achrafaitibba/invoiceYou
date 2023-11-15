package com.onxshield.invoiceyou.invoicestatement.dto.request;


public record updateInvoiceRequest(

        invoiceRequest basicInvoice,
        Boolean printed,
        String invoiceAction,
        String invoiceStatus,
        String invoiceFile


) {
}
