package com.ecommerce.blossom.api.blossom_api.entities;

import com.ecommerce.blossom.api.blossom_api.enums.PaymentMethod;
import com.ecommerce.blossom.api.blossom_api.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    private Double amountPaid;

    private LocalDateTime paymentDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}

