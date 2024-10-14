package com.Myfirstshopping.website.Myfirstshopping.Controller;

import com.Myfirstshopping.website.Myfirstshopping.Service.SellerService;
import com.Myfirstshopping.website.Myfirstshopping.exception.AccessNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.InvalidProductID;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

     @Autowired
    private SellerService sellerService;
@PostMapping("/product/register")
public void addProduct(@RequestBody Product product, @RequestParam UUID sellerID){

    sellerService.addProduct(product,sellerID);

}
@DeleteMapping("/product/remove")

    public String removeProduct(@RequestParam UUID sellerID,@RequestParam UUID productID){
     try{

         String result= sellerService.removeProduct(sellerID,productID);
         return result;
     }catch(UserNotFound userNotFound){
      return userNotFound.getMessage();
     }catch(InvalidProductID invalidProductID){
         return invalidProductID.getMessage();
     }





}


}
