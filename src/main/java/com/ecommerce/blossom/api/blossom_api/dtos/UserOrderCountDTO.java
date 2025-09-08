package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.Data;

@Data
public class UserOrderCountDTO {
    String name;
    String email;
    Long totalOrders;

    public UserOrderCountDTO(String name, String email, Long totalOrders) {
        this.name = name;
        this.email = email;
        this.totalOrders = totalOrders;
    }
}
