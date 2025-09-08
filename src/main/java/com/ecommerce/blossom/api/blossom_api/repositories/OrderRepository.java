package com.ecommerce.blossom.api.blossom_api.repositories;

import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.email = :email")
    List<Order> findOrdersByUserEmail(@Param("email") String email);

    Page<Order> findByUserEmail(String email, Pageable pageable);

    Page<Order> findByUserEmailAndStatus(String email, OrderStatus status, Pageable pageable);

}