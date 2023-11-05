package com.onxshield.invoiceyou.invoicestatement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class merchandiseResponse {
    private String productName;
    private String unit;
    private Long quantity;
    private Long sellPrice;
    private Long totalTTC;
}
