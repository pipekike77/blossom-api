package com.ecommerce.blossom.api.blossom_api.listeners;

import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.events.OrderCreatedEvent;
import com.ecommerce.blossom.api.blossom_api.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    @Autowired
    private PaymentService paymentService;

    @Async
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) throws InterruptedException {
        Order order = event.getOrder();

        // Simulate processing time (e.g.: payment gateway)
        Thread.sleep(15000);

        // Simular resultado aleatorio del pago
        boolean success = Math.random() > 0.3;

        paymentService.processPayment(order, success);
    }
}
