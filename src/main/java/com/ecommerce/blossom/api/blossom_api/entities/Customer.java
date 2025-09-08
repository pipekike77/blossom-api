package com.ecommerce.blossom.api.blossom_api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {

    private String address;
}
