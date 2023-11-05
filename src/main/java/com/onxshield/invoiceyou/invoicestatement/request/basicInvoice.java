package com.onxshield.invoiceyou.invoicestatement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class basicInvoice {
    private String invoiceId;
    private Date invoiceDate;
    private Long clientId;
    private Long totalTTC;
    private String paymentMethod;
    private String checkNumber;
    private ArrayList<merchandiseRequest> merchandiseList;


}
