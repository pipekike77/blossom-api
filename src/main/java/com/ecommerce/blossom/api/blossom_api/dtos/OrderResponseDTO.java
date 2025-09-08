package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private Double total;
    private String userEmail;
    private List<OrderProductDTO> products;
}
