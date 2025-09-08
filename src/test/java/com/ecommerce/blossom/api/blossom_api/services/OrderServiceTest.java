package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.CreateOrderRequestDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.OrderHistoryDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.OrderResponseDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductQuantityDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Customer;
import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.entities.User;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.events.OrderCreatedEvent;
import com.ecommerce.blossom.api.blossom_api.mappers.OrderMapper;
import com.ecommerce.blossom.api.blossom_api.repositories.OrderRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.ProductRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        user= new Customer();
        user.setId(1L);
        user.setEmail("user@test.com");

        product = new Product();
        product.setId(10L);
        product.setName("Product A");
        product.setPrice(100.0);

    }

    @Test
    void createOrder(){

        CreateOrderRequestDTO request = new CreateOrderRequestDTO();
        request.setUserId(1L);
        request.setProducts(Collections.singletonList(new ProductQuantityDTO(10L, 2)));

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(user));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(99L);
            return savedOrder;
        });

        // Act
        OrderResponseDTO response = orderService.createOrder(request);

        // Assert
        assertNotNull(response);
        assertEquals(99L, response.getOrderId());
        assertEquals(200.0, response.getTotal());
        assertEquals("user@test.com", response.getUserEmail());
        assertEquals(1, response.getProducts().size());
        assertEquals("Product A", response.getProducts().get(0).getProductName());

        verify(orderRepository).save(any(Order.class));

    }

    @Test
    void getUserOrderHistory(){
        String email = "user@test.com";
        OrderStatus status = OrderStatus.PENDING;
        Pageable pageable = PageRequest.of(0, 10);

        Order order = new Order();
        order.setId(1L);

        Page<Order> page = new PageImpl<>(List.of(order));

        when(orderRepository.findByUserEmailAndStatus(email, status, pageable)).thenReturn(page);
        when(orderMapper.orderToDTO(order)).thenReturn(new OrderHistoryDTO(1L, status, 100.0, LocalDateTime.now(), null));

        // Act
        Page<OrderHistoryDTO> result = orderService.getUserOrderHistory(email, Optional.of(status), pageable);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(orderRepository).findByUserEmailAndStatus(email, status, pageable);

    }
}