package com.ecommerce.blossom.api.blossom_api.entities;

import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
}