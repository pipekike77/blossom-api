package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(hidden = true)
public class ProductSearchRequestDTO {
    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;
    private String sortBy = "createdAt"; // o "price"
    private String sortDirection = "desc"; // o "asc"
    private int page = 0;
    private int size = 10;
}
