package com.Myfirstshopping.website.Myfirstshopping.Service;


import com.Myfirstshopping.website.Myfirstshopping.Repositery.ProductRepositery;
import com.Myfirstshopping.website.Myfirstshopping.exception.AccessNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.InvalidProductID;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.model.AppUser;
import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@ToString
@Service
public class SellerService {
    @Autowired
    private CommonUserService commonuserService;
    @Autowired
   private ProductRepositery productRepositery;
    @Autowired
    private ProductService productService;
    public void addProduct(Product product, UUID sellerID){
       AppUser user= commonuserService.getUserById(sellerID);
     if(user==null){
         throw new UserNotFound(String.format("user not found with id %s",sellerID.toString()));
     }
     if(user.getUserType().equals("BUYER")){
         throw new AccessNotFound(String.format("Sorry you do not have access to add %s",sellerID.toString()));
     }
      product.setSeller(user);
     productRepositery.save(product);
    }

    public String removeProduct(UUID sellerID, UUID productID){


       Boolean isSeller = commonuserService.isSeller(sellerID);
     if(isSeller == null){
         throw new UserNotFound(String.format("User with this id %s does not exisr",sellerID.toString()));
     }
        if(isSeller == false){
            throw new AccessNotFound(String.format("User with this id %s does not have access to delete ",sellerID.toString()));
        }
        boolean validProduct= productService.validateProductID(productID);
        if(validProduct=false){
         throw new InvalidProductID(String.format("product with this id %s is invalid",productID.toString()));
        }

        Product product =productService.getProductByID(productID);
       AppUser owner=product.getSeller();
       if(!owner.getId().equals(sellerID)){
           throw new AccessNotFound(String.format("User with this id %s does not have access to delete the product id %s ",owner.getName(),product.getProductName()));

       }
       productService.removeProduct(product);
       return String.format("USer with name %s removed product with id %",owner.getName(),product.getProductName());
    }



}
