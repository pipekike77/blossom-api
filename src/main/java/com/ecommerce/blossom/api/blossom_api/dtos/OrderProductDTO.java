package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductDTO {
    private String productName;
    private int quantity;
    private double price;
}
