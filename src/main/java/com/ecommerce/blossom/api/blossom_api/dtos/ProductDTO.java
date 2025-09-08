package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(hidden = true)
public class ProductDTO {

    private Long id;

    @NotNull(message = "Product name is required")
    private String name;

    @NotNull(message = "Product description is required")
    private String description;

    @NotNull(message = "Product category is required")
    private String category;

    @NotNull(message = "Product price is required")
    private Double price;
}
