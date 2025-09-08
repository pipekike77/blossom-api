package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(hidden = true)
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
