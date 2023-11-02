package com.onxshield.invoiceyou.invoicestatement.request;

import com.onxshield.invoiceyou.invoicestatement.model.merchandise;
import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class basicInvoiceRequest {
        //todo review this,eg: no ICE for individuals
        private String invoiceId;
        private Date invoiceDate;
        private String clientName;
        private String ICE;
        private List<merchandise> merchandiseList;
        private Long totalTTC;
        private paymentMethod paymentMethod;
    }
