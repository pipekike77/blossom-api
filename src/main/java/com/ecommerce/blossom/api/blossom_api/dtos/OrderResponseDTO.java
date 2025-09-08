package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(hidden = true)
public class OrderResponseDTO {
    private Long orderId;
    private Double total;
    private String userEmail;
    private List<OrderProductDTO> products;
}
