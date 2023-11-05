package com.onxshield.invoiceyou.invoicestatement.request;

import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
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
public class basicInvoiceRequest {
    private String invoiceId;
    private Date invoiceDate;
    private Long clientId;
    private Long totalTTC;
    private paymentMethod paymentMethod;
    private Integer checkNumber;
    private ArrayList<merchandiseRequest> merchandiseList;


}
