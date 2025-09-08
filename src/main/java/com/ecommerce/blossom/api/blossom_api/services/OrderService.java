package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.*;
import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.entities.OrderProduct;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.entities.User;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.events.OrderCreatedEvent;
import com.ecommerce.blossom.api.blossom_api.mappers.OrderMapper;
import com.ecommerce.blossom.api.blossom_api.repositories.OrderRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.ProductRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderMapper orderMapper;


    public OrderResponseDTO createOrder(CreateOrderRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();
        double total = 0;

        for (ProductQuantityDTO pq : request.getProducts()) {
            Product product = productRepository.findById(pq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(pq.getQuantity());

            orderProducts.add(orderProduct);

            double itemTotal = product.getPrice() * pq.getQuantity();
            total += itemTotal;

            orderProductDTOs.add(new OrderProductDTO(
                    product.getName(),
                    pq.getQuantity(),
                    product.getPrice()
            ));
        }

        order.setOrderProducts(orderProducts);
        order.setTotal(total);

        orderRepository.save(order);

        //Lanzar evento de pago
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(order));

        return new OrderResponseDTO(
                order.getId(),
                total,
                user.getEmail(),
                orderProductDTOs
        );
    }

    public Page<OrderHistoryDTO> getUserOrderHistory(String email, Optional<OrderStatus> status, Pageable pageable) {
        Page<?> ordersPage = status
                .map(s -> orderRepository.findByUserEmailAndStatus(email, s, pageable))
                .orElseGet(() -> orderRepository.findByUserEmail(email, pageable));

        return ordersPage.map(order -> orderMapper.orderToDTO((com.ecommerce.blossom.api.blossom_api.entities.Order) order));
    }
}
