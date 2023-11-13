package com.onxshield.invoiceyou.invoicestatement.dto.response;

import com.onxshield.invoiceyou.invoicestatement.model.inventory;

public record inventoryResponse(
       productResponse product,
       Long inventoryId,
       Double availability,
       Double buyPrice,
       Double sellPrice

) {
}
