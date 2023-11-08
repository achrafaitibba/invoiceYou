package com.onxshield.invoiceyou.invoicestatement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class inventoryRequest {
    private Double availability;
    private Double buyPrice;
    private Double sellPrice;
}
