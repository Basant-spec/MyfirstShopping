package com.Myfirstshopping.website.Myfirstshopping.Service;

import com.Myfirstshopping.website.Myfirstshopping.Repositery.ProductRepositery;
import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
     @Autowired
    private ProductRepositery productRepositery;
     public Product getProductByID(UUID productID){

         return productRepositery.findById(productID).orElse(null);


     }
    public Boolean validateProductID(UUID productID){
        Product product = getProductByID(productID);
        if(product == null){
            return false;
        }
        return true;
    }
    public void removeProduct(Product product){
         productRepositery.delete(product);
    }


}
