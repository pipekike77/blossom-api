package com.ecommerce.blossom.api.blossom_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductKey implements Serializable {

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductKey that)) return false;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
