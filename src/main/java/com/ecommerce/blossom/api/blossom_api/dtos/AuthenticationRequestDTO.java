package com.ecommerce.blossom.api.blossom_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    @NotNull(message = "User email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String password;
}
