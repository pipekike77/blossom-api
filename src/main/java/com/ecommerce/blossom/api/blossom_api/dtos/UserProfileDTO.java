package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(hidden = true)
public class UserProfileDTO {
    private String name;
    private String email;
    private String role;
    private String address;
    private String department;
    private List<OrderDTO> orders;
}
