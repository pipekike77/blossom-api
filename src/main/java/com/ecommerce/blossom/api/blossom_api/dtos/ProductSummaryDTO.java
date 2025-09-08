package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.Data;

@Data
public class ProductSummaryDTO {

    private String name;
    private Integer quantity;
    private Double price;
}
