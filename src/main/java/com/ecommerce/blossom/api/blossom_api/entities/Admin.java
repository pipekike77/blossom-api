package com.ecommerce.blossom.api.blossom_api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {
    private String department;
}
