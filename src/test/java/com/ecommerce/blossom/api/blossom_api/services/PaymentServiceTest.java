package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.enums.PaymentMethod;
import com.ecommerce.blossom.api.blossom_api.enums.PaymentStatus;
import com.ecommerce.blossom.api.blossom_api.repositories.OrderRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void processPayment() {
        Order order = new Order();
        order.setId(1L);
        order.setTotal(150.0);
        order.setStatus(OrderStatus.PENDING);

        paymentService.processPayment(order, true);

        // Verifica que el pago fue guardado con PAID
        verify(paymentRepository).save(argThat(p ->
                p.getOrder().equals(order) &&
                        p.getAmountPaid() == 150.0 &&
                        p.getPaymentMethod() == PaymentMethod.SIMULATED &&
                        p.getPaymentStatus() == PaymentStatus.PAID
        ));

        // Verifica que el estado de la orden fue actualizado
        assertEquals(OrderStatus.PAID, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void processManualPayment() {
        Order order = new Order();
        order.setId(4L);
        order.setTotal(400.0);
        order.setStatus(OrderStatus.PENDING);

        when(orderRepository.findById(4L)).thenReturn(Optional.of(order));

        paymentService.processManualPayment(4L, false);

        verify(paymentRepository).save(argThat(p ->
                p.getOrder().equals(order) &&
                        p.getPaymentStatus() == PaymentStatus.FAILED
        ));

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        verify(orderRepository).save(order);
    }
}