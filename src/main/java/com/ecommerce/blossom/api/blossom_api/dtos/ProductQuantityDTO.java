package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(hidden = true)
public class ProductQuantityDTO {
    Long productId;
    int quantity;
}
