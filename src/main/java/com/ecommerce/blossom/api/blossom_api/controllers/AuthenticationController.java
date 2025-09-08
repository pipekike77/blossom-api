package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.AuthenticationRequestDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.AuthenticationResponseDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.RegisterRequestDTO;
import com.ecommerce.blossom.api.blossom_api.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
