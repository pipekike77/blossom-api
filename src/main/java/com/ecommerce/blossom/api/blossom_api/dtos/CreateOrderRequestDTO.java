package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(hidden = true)
public class CreateOrderRequestDTO {

    @NotNull(message = "User ID is required")
    Long userId;

    @NotEmpty(message = "Order must contain at least one product")
    List<ProductQuantityDTO > products;
}
