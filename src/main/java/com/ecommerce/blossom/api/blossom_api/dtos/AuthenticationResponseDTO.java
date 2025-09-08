package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(hidden = true)
public class AuthenticationResponseDTO {
    private String token;
    private String role;
    private String email;
}
