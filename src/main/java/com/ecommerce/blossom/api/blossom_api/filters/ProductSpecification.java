package com.ecommerce.blossom.api.blossom_api.filters;

import com.ecommerce.blossom.api.blossom_api.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class ProductSpecification {

    public static Specification<Product> withFilters(
            String name,
            String category,
            Double minPrice,
            Double maxPrice
    ) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (category != null && !category.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("category"), category));
            }

            if (minPrice != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return predicate;
        };
    }
}
