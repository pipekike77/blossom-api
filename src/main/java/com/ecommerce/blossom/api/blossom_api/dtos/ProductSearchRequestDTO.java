package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.Data;

@Data
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
