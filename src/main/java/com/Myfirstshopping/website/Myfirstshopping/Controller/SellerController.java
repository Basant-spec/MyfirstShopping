package com.Myfirstshopping.website.Myfirstshopping.Controller;

import com.Myfirstshopping.website.Myfirstshopping.ResponseBody.ProductResponseBody;
import com.Myfirstshopping.website.Myfirstshopping.Service.SellerService;
import com.Myfirstshopping.website.Myfirstshopping.exception.AccessNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.InvalidProductID;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.model.Product;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/product/all")
    public List<ProductResponseBody> getAllProducts(@RequestParam UUID sellerID){
        return sellerService.getAllProductBySellerID(sellerID);
    }
    @GetMapping("/product/analytics")
    public ResponseEntity getAnalytics(@RequestParam UUID sellerID,
                                       @RequestParam UUID productID,
                                       @RequestParam String analytics ){
  try {
      if (analytics.equals("TOTALQUANTITYSOLD")) {
        return new ResponseEntity(sellerService.getProductTotalQuantitySoldByID(productID, sellerID), HttpStatus.OK);
      } else if (analytics.equals("RATING")) {
       return new ResponseEntity(sellerService.getProductRatingByID(productID, sellerID),HttpStatus.OK);
      } else {
       return new ResponseEntity("Invalid Analytics Bro",HttpStatus.OK);
      }
  }
     catch(UserNotFound userNotFound){
      return new ResponseEntity(userNotFound.getMessage(),HttpStatus.OK);
    }
   catch(InvalidProductID invalidProductID){
      return new ResponseEntity(invalidProductID.getMessage(),HttpStatus.OK);
    }catch(AccessNotFound accessNotFound){
      return new ResponseEntity(accessNotFound.getMessage(),HttpStatus.OK);
    }




    }






}
