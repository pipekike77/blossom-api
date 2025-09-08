package com.ecommerce.blossom.api.blossom_api.repositories;

import com.ecommerce.blossom.api.blossom_api.dtos.UserOrderCountDTO;
import com.ecommerce.blossom.api.blossom_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
    SELECT new com.ecommerce.blossom.api.blossom_api.dtos.UserOrderCountDTO(u.name, u.email, COUNT(o))
    FROM User u
    JOIN u.orders o
    GROUP BY u.id, u.name, u.email
    ORDER BY COUNT(o) DESC
""")
    List<UserOrderCountDTO> findUsersWithMostOrders();

}
