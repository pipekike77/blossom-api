package com.ecommerce.blossom.api.blossom_api.dtos;

import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderHistoryDTO {
    private Long orderId;
    private OrderStatus status;
    private Double total;
    private LocalDateTime createdAt;
    private List<ProductSummaryDTO> products;
}
