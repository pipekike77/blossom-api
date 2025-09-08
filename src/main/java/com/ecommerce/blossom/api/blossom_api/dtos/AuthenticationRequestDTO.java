package com.ecommerce.blossom.api.blossom_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(hidden = true)
public class AuthenticationRequestDTO {

    @NotNull(message = "User email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String password;
}
