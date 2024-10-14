package com.Myfirstshopping.website.Myfirstshopping.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   @Column (nullable = false,unique = true)
   private String productName;
   private int price;
    @Column (nullable = false)
   private int quantity;
   private double rating;
    @Column (nullable = false)
   private int totalSoldQuantity;
    @Column (nullable = false, unique=true)
   private String category;
   @ManyToOne
    AppUser seller;
}
