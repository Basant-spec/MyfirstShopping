package com.Myfirstshopping.website.Myfirstshopping.Repositery;


import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepositery extends JpaRepository<Product, UUID> {

      public Product findByProductName(String productName);
    @Query(value = "select * from product where seller_id=:sellerID",nativeQuery = true)
    public List<Product> getAllProductBySellerID(UUID sellerID);

    @Query(value = "select * from product where rating =:maximumRating order by price asc limit 10",nativeQuery = true)
    public List<Product> getProductByMaximumRatingAndAsc(double maximumRating);

    @Query(value = "select * from product where rating =:maximumRating order by price desc limit 10",nativeQuery = true)
    public List<Product> getProductByMaximumRatingAndDesc(double maximumRating);

    @Query(value = "select * from product where rating =:minimumRating order by price asc limit 10",nativeQuery = true)
    public List<Product> getProductByMinimumRatingAndAsc(double minimumRating);

    @Query(value = "select * from product where rating =:minimumRating order by price desc limit 10",nativeQuery = true)
    public List<Product> getProductByMinimumRatingAndDesc(double minimumRating);

    @Query(value = "select max(rating) from product", nativeQuery = true)
    public Double getMaxRating();

    @Query(value = "select min(rating) from product", nativeQuery = true)
    public Double getMinRating();
    @Query(value = "select * from product order by price asc limit 10", nativeQuery = true)
    public List<Product> getProductInAsc();

    @Query(value = "select * from product order by price Desc limit 10", nativeQuery = true)
    public List<Product> getProductInDesc();

    @Query(value = "select * from product where rating=:rating limit 10", nativeQuery = true)
    public List<Product> getProductByRating(double rating);

    @Query(value = "select * from product limit 10", nativeQuery = true)
    public List<Product> getProducts();
  @Transactional
  @Modifying
   @Query(value="update product set quantity=:quantity,total_Sold_Quantity=:totalSoldQuantity where id=:id")
    public void updateQuantity(int quantity,UUID id,int totalSoldQuantity);

}
