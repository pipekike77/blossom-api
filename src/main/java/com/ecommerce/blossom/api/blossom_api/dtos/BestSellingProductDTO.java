package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class BestSellingProductDTO {
    private String name;
    private Long totalSold;
}
