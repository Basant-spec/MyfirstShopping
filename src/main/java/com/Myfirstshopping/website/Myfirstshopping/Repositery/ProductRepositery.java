package com.Myfirstshopping.website.Myfirstshopping.Repositery;


import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepositery extends JpaRepository<Product, UUID> {


    @Query(value = "select * from product where seller_id=:sellerID",nativeQuery = true)
    public List<Product> getAllProductBySellerID(UUID sellerID);

}
