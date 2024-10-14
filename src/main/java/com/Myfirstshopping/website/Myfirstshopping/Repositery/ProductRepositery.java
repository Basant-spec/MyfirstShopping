package com.Myfirstshopping.website.Myfirstshopping.Repositery;


import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepositery extends JpaRepository<Product, UUID> {

}
