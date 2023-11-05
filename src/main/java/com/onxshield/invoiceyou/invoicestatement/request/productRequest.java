package com.onxshield.invoiceyou.invoicestatement.request;

import com.onxshield.invoiceyou.invoicestatement.model.category;
import com.onxshield.invoiceyou.invoicestatement.model.unit;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class productRequest {
    private String name;
    private unit unit;
    private String[] categoryList;
}
