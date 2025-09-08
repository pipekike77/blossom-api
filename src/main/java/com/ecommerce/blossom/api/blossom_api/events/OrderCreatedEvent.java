package com.ecommerce.blossom.api.blossom_api.events;

import com.ecommerce.blossom.api.blossom_api.entities.Order;

public class OrderCreatedEvent {

    private final Order order;

    public OrderCreatedEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
