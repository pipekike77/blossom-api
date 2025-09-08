package com.ecommerce.blossom.api.blossom_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct {

    @EmbeddedId
    private OrderProductKey id = new OrderProductKey();

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    private Integer quantity;
}

