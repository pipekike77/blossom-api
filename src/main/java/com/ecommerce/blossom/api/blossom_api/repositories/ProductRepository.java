package com.ecommerce.blossom.api.blossom_api.repositories;

import com.ecommerce.blossom.api.blossom_api.dtos.BestSellingProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

    @Query("""
    SELECT new com.ecommerce.blossom.api.blossom_api.dtos.BestSellingProductDTO(
        op.product.name,
        SUM(op.quantity)
    )
    FROM Order o
    JOIN o.orderProducts op
    GROUP BY op.product.name
    ORDER BY SUM(op.quantity) DESC
""")
    List<BestSellingProductDTO> findBestSellingProducts();

}
