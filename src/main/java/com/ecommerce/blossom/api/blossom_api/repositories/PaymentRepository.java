package com.ecommerce.blossom.api.blossom_api.repositories;

import com.ecommerce.blossom.api.blossom_api.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}