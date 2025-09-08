package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.AuthenticationRequestDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.AuthenticationResponseDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.RegisterRequestDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Admin;
import com.ecommerce.blossom.api.blossom_api.entities.Customer;
import com.ecommerce.blossom.api.blossom_api.entities.User;
import com.ecommerce.blossom.api.blossom_api.enums.Role;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import com.ecommerce.blossom.api.blossom_api.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user;

        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            Admin admin = new Admin();
            admin.setDepartment(request.getDepartment());
            user = admin;
        } else {
            Customer customer = new Customer();
            customer.setAddress(request.getAddress());
            user = customer;
        }

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        user.setName(request.getName());

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().name());

        return new AuthenticationResponseDTO(token, user.getRole().name(), user.getEmail());
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().name());

        return new AuthenticationResponseDTO(token, user.getRole().name(), user.getEmail());
    }
}
