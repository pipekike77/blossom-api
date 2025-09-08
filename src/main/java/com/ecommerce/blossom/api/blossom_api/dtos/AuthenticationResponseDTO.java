package com.ecommerce.blossom.api.blossom_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String token;
    private String role;
    private String email;
}
