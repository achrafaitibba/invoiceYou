package com.onxshield.invoiceyou.invoicestatement.response;

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
public class basicInvoiceResponse {

    private String invoiceId;
    private Date invoiceDate;
    private String clientName;
    private Long ICE;
    private Double totalTTC;
    private Double TVA;
    private ArrayList<merchandiseResponse> merchandiseList;
    private String spelledTotal;
    private String paymentMethod;
    private Integer checkNumber;

}
