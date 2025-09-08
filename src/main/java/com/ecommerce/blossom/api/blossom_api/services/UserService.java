package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.OrderDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.OrderProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.UserOrderCountDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.UserProfileDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Admin;
import com.ecommerce.blossom.api.blossom_api.entities.Customer;
import com.ecommerce.blossom.api.blossom_api.entities.User;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserProfileDTO getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderDTO> orders = user.getOrders().stream().map(order -> {
            List<OrderProductDTO> products = order.getOrderProducts().stream()
                    .map(op -> new OrderProductDTO(
                            op.getProduct().getName(),
                            op.getQuantity(),
                            op.getProduct().getPrice()))
                    .toList();

            return new OrderDTO(
                    order.getId(),
                    order.getCreatedAt(),
                    order.getStatus(),
                    products
            );
        }).toList();

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setName(user.getName());
        userProfileDTO.setEmail(user.getEmail());
        userProfileDTO.setOrders(orders);
        userProfileDTO.setRole(user.getRole().name());
        if ("ADMIN".equalsIgnoreCase(user.getRole().name())) {
            userProfileDTO.setDepartment(((Admin)user).getDepartment());
        }else{
            userProfileDTO.setAddress(((Customer)user).getAddress());
        }

        return userProfileDTO;
    }

    public List<UserOrderCountDTO> getUsersWithMostOrders() {
        return userRepository.findUsersWithMostOrders();
    }

}
