package com.ecommerce.blossom.api.blossom_api.dtos;

import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderProductDTO> orderProducts;
}
