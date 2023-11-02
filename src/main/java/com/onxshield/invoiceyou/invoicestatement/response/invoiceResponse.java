package com.onxshield.invoiceyou.invoicestatement.response;

import com.onxshield.invoiceyou.invoicestatement.model.paymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class invoiceResponse {
        private String invoiceId;
        private Date invoiceDate;
        private String clientName;
        private String clientICE;
        private Long totalTTC;
        private Long TVA;
        private paymentMethod paymentMethod;
        private String bankName;
        private Integer checkNumber;
        private Date paymentDate;
        private String printed;
        private String action;
        private String status;
        private String invoiceFile;
}
