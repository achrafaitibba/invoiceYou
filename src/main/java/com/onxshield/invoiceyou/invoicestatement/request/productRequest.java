package com.onxshield.invoiceyou.invoicestatement.request;

import com.onxshield.invoiceyou.invoicestatement.model.unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class productRequest {
    private String name;
    private unit unit;
    private String[] categoryList;
}
