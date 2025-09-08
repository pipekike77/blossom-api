package com.ecommerce.blossom.api.blossom_api.config;

import com.ecommerce.blossom.api.blossom_api.entities.Admin;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.enums.Role;
import com.ecommerce.blossom.api.blossom_api.repositories.ProductRepository;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@blossom.com";

        Admin admin;

        if (!userRepository.existsByEmail(adminEmail)) {
            admin = new Admin();
            admin.setName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123")); // Hashed password
            admin.setRole(Role.ADMIN);
            admin.setDepartment("Security");
            userRepository.save(admin);

            System.out.println("✅ Admin user created.");
        } else {
            admin = (Admin) userRepository.findByEmail(adminEmail).orElseThrow();
            System.out.println("ℹ️ Admin user already exists.");
        }

        // ✅ Crear productos si aún no existen
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product(null, "Soccer Ball", "Professional size 5 soccer ball", "Soccer", 25.00, LocalDateTime.now().minusDays(1), null),
                    new Product(null, "Basketball", "Official NBA size 7 basketball", "Basketball", 29.99, LocalDateTime.now().minusDays(1), null),
                    new Product(null, "Boxing Gloves", "Training gloves, 12 oz", "Boxing", 35.00, LocalDateTime.now().minusDays(2), null),
                    new Product(null, "Tennis Racket", "Graphite adult tennis racket", "Tennis", 50.00, LocalDateTime.now().minusDays(2), null),
                    new Product(null, "Mountain Bike", "Front suspension mountain bike", "Cycling", 300.00, LocalDateTime.now().minusDays(2), null),
                    new Product(null, "Nike Sports T-Shirt", "Dri-FIT training t-shirt", "Sportswear", 19.99, LocalDateTime.now().minusDays(2), null),
                    new Product(null, "Adjustable Dumbbells", "Set of 2 dumbbells, 10kg each", "Weights", 60.00, LocalDateTime.now().minusDays(6), null),
                    new Product(null, "Yoga Mat", "6mm non-slip yoga mat", "Yoga", 15.50, LocalDateTime.now().minusDays(6), null),
                    new Product(null, "Adidas Running Shoes", "Ultralight running sneakers", "Running", 75.00, LocalDateTime.now().minusDays(6), null),
                    new Product(null, "Cycling Helmet", "Adjustable helmet with ventilation", "Cycling", 22.00, LocalDateTime.now().minusDays(6), null)
            );

            productRepository.saveAll(products);
            System.out.println("10 products created succesfully.");
        } else {
            System.out.println("Products already exist in the database.");
        }
    }
}
