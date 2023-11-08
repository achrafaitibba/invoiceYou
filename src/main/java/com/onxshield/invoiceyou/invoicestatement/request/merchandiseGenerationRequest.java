package com.onxshield.invoiceyou.invoicestatement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class merchandiseGenerationRequest {
    private Double totalTTC;
    private Integer productsCount;
    private String category;
}
