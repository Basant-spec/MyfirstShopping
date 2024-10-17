package com.Myfirstshopping.website.Myfirstshopping.Controller;


import com.Myfirstshopping.website.Myfirstshopping.ResponseBody.BillResponseBody;
import com.Myfirstshopping.website.Myfirstshopping.ResponseBody.ProductResponseBody;
import com.Myfirstshopping.website.Myfirstshopping.Service.BuyerService;
import com.Myfirstshopping.website.Myfirstshopping.exception.AccessNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.InvalidOperation;
import com.Myfirstshopping.website.Myfirstshopping.exception.ProductNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.requestBody.OrderProductRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
      @Autowired
    private BuyerService buyerService;

    @GetMapping("/product/all")
    public ResponseEntity getAllProducts(@RequestParam(required = false) String filter, @RequestParam(required = false) String sort){

         if(filter==null && sort ==null){
             List<ProductResponseBody> products = buyerService.getProducts();
             return new ResponseEntity(products,
                     HttpStatus.OK);

         }else if(filter!=null && sort==null){
             List<ProductResponseBody> products = buyerService.getProductByRating(filter);
             return new ResponseEntity(products,
                     HttpStatus.OK);

         }else if(sort!=null && filter ==null){
             List<ProductResponseBody> products = buyerService.getProduct(sort);
             return new ResponseEntity(products,
                     HttpStatus.OK);

         }else{

             List<ProductResponseBody> products = buyerService.getProduct(sort);
             return new ResponseEntity(products,
                     HttpStatus.OK);

         }

    }
    @PostMapping("/product/buy")
    public ResponseEntity placeOrder(@RequestParam UUID buyerID, @RequestBody List<OrderProductRequestBody> products){
           try{
               BillResponseBody bill= buyerService.purchaseProduct(buyerID,products);
               return new ResponseEntity<>(bill,HttpStatus.CREATED);

           }catch(UserNotFound userNotFound){
               return new ResponseEntity(userNotFound.getMessage(),HttpStatus.NOT_FOUND);

        }catch (AccessNotFound accessNotFound){

                return new ResponseEntity(accessNotFound.getMessage(),HttpStatus.UNAUTHORIZED);}
           catch(InvalidOperation invalidOperation){
               return new ResponseEntity(invalidOperation.getMessage(),HttpStatus.BAD_REQUEST);
           } catch(ProductNotFound productNotFound){
               return new ResponseEntity(productNotFound.getMessage(),HttpStatus.NOT_FOUND);
           }







    }






}
