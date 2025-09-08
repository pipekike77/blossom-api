package com.ecommerce.blossom.api.blossom_api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ManualPaymentRequestDTO {

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Success flag is required")
    private Boolean success;
}
