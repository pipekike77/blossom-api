package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserProfileDTO {
    private String name;
    private String email;
    private String role;
    private String address;
    private String department;
    private List<OrderDTO> orders;
}
