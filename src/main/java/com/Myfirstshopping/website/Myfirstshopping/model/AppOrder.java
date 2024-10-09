package com.Myfirstshopping.website.Myfirstshopping.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppOrder {
    @Id
    private UUID id;
    @ManyToOne
    AppUser buyer;
    @OneToMany
    List<Product> product;
    @Column(nullable=false)
    int totalQuantity;
    @Column(nullable=false)
    int totalPrice;










}
