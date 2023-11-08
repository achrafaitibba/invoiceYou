package com.onxshield.invoiceyou.invoicestatement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class merchandiseGenerationResponse {
    private Long productId;
    private Double quantity;
}
