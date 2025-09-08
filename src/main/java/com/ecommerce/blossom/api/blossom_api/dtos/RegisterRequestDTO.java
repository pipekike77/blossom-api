package com.ecommerce.blossom.api.blossom_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotNull(message = "User full name is required")
    private String name;

    @NotNull(message = "User email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit"
    )
    private String password;

    private String role; // ADMIN o CUSTOMER
    private String address;
    private String department;
}
