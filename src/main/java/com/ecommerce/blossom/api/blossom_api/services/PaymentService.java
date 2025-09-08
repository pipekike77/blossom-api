package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.entities.Payment;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.enums.PaymentMethod;
import com.ecommerce.blossom.api.blossom_api.enums.PaymentStatus;
import com.ecommerce.blossom.api.blossom_api.repositories.OrderRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public void processPayment(Order order, boolean success) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmountPaid(order.getTotal());
        payment.setPaymentMethod(PaymentMethod.SIMULATED);
        //payment.setPaidAt(LocalDateTime.now());
        payment.setPaymentStatus(success ? PaymentStatus.PAID : PaymentStatus.FAILED);

        paymentRepository.save(payment);

        // Actualizar estado de la orden
        order.setStatus(success ? OrderStatus.PAID : OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    public void processManualPayment(Long orderId, boolean success) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        /*
        Deja sobrescribir el metodo de pago
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Order already processed");
        }
         */

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmountPaid(order.getTotal());

        // Método de pago aleatorio (excluye SIMULATED)
        PaymentMethod[] methods = PaymentMethod.values();
        PaymentMethod randomMethod = methods[new Random().nextInt(4)];
        payment.setPaymentMethod(randomMethod);

        payment.setPaymentStatus(success ? PaymentStatus.PAID : PaymentStatus.FAILED);

        paymentRepository.save(payment);

        // Actualizar estado de la orden
        order.setStatus(success ? OrderStatus.PAID : OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
