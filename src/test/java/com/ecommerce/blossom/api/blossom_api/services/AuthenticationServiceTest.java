package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.AuthenticationRequestDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.AuthenticationResponseDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.RegisterRequestDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Customer;
import com.ecommerce.blossom.api.blossom_api.entities.User;
import com.ecommerce.blossom.api.blossom_api.enums.Role;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import com.ecommerce.blossom.api.blossom_api.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthenticationService authenticationService;

    private RegisterRequestDTO registerRequestDTO;
    private AuthenticationRequestDTO authenticationRequestDTO;

    @BeforeEach
    void setUp() {

        registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setEmail("test@correo.com");
        registerRequestDTO.setPassword("123456");
        registerRequestDTO.setRole("CUSTOMER");
        registerRequestDTO.setAddress("Calle 123");
        registerRequestDTO.setName("Juan");

        authenticationRequestDTO = new AuthenticationRequestDTO();
        authenticationRequestDTO.setEmail("user@correo.com");
        authenticationRequestDTO.setPassword("123456");
    }

    @Test
    void register() {

        when(userRepository.findByEmail(registerRequestDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registerRequestDTO.getPassword())).thenReturn("encodedPass");
        when(jwtUtils.generateToken(anyString(), anyString())).thenReturn("fake-jwt");

        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.register(registerRequestDTO);

        // Assert
        assertNotNull(authenticationResponseDTO);
        assertEquals("fake-jwt", authenticationResponseDTO.getToken());
        assertEquals("CUSTOMER", authenticationResponseDTO.getRole());
        assertEquals("test@correo.com", authenticationResponseDTO.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        registerRequestDTO.setEmail("exists@correo.com");

        when(userRepository.findByEmail(registerRequestDTO.getEmail()))
                .thenReturn(Optional.of(new Customer()));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authenticationService.register(registerRequestDTO));
        verify(userRepository, never()).save(any());
    }

    @Test
    void login() {

        User user = new Customer();
        user.setEmail("user@correo.com");
        user.setRole(Role.CUSTOMER);
        user.setName("Juan");

        when(userRepository.findByEmail(authenticationRequestDTO.getEmail())).thenReturn(Optional.of(user));
        when(jwtUtils.generateToken(anyString(), anyString())).thenReturn("fake-jwt");

        // Act
        AuthenticationResponseDTO response = authenticationService.login(authenticationRequestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("fake-jwt", response.getToken());
        assertEquals("CUSTOMER", response.getRole());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

    }

}