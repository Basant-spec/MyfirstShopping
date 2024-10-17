package com.Myfirstshopping.website.Myfirstshopping.Service;


import com.Myfirstshopping.website.Myfirstshopping.Repositery.OrderRepositery;
import com.Myfirstshopping.website.Myfirstshopping.Repositery.ProductRepositery;
import com.Myfirstshopping.website.Myfirstshopping.ResponseBody.BillResponseBody;
import com.Myfirstshopping.website.Myfirstshopping.ResponseBody.ProductResponseBody;
import com.Myfirstshopping.website.Myfirstshopping.ResponseBody.SpecificProductOrderDetails;
import com.Myfirstshopping.website.Myfirstshopping.exception.AccessNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.InvalidOperation;
import com.Myfirstshopping.website.Myfirstshopping.exception.ProductNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.model.AppOrder;
import com.Myfirstshopping.website.Myfirstshopping.model.AppUser;
import com.Myfirstshopping.website.Myfirstshopping.model.Product;
import com.Myfirstshopping.website.Myfirstshopping.requestBody.OrderProductRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    private CommonUserService commonUserService;
    @Autowired
    private ProductUtilService productUtilService;
    @Autowired
    private ProductRepositery productRepositery;
    @Autowired
    private OrderRepositery orderRepositery;
   public List<ProductResponseBody> getProduct(String filter, String sort){
       if(filter.equals("MAXIMUM-RATING")){
           Double maxRating = productRepositery.getMaxRating();
         if(sort.equals("asc")){

             List<Product> products=productRepositery.getProductByMaximumRatingAndAsc(maxRating);
             return productUtilService.convertProductToProductResponse(products);
         }else{
             List<Product> products=productRepositery.getProductByMaximumRatingAndDesc(maxRating);
             return productUtilService.convertProductToProductResponse(products);
         }

       }else{
           Double minimumRating = productRepositery.getMinRating();
           if(sort.equals("asc")){

            List<Product> products= productRepositery.getProductByMinimumRatingAndAsc(minimumRating);
               return productUtilService.convertProductToProductResponse(products);
           }else{
               List<Product> products= productRepositery.getProductByMinimumRatingAndDesc(minimumRating);
               return productUtilService.convertProductToProductResponse(products);
           }

       }
   }

    public  List<ProductResponseBody> getProduct(String sort) {
        if (sort.equals("asc")) {
            List<Product> products = productRepositery.getProductInAsc();
            return productUtilService.convertProductToProductResponse(products);
        } else {
            List<Product> products = productRepositery.getProductInAsc();
            return productUtilService.convertProductToProductResponse(products);
        }

    }
    public  List<ProductResponseBody> getProductByRating(String rating) {
        if (rating.equals("MAXIMUM-RATING")) {
            Double maxRating= productRepositery.getMaxRating();
            List<Product> products = productRepositery.getProductByRating(maxRating);
            return productUtilService.convertProductToProductResponse(products);
        } else {
            Double minRating= productRepositery.getMinRating();
            List<Product> products = productRepositery.getProductByRating(minRating);
            return productUtilService.convertProductToProductResponse(products);
        }

    }
    public List<ProductResponseBody> getProducts(){
        List<Product> products = productRepositery.getProducts();
        return productUtilService.convertProductToProductResponse(products);
    }

    public BillResponseBody purchaseProduct(UUID buyerID, List<OrderProductRequestBody> orderProducts){

        AppUser buyer=commonUserService.getUserById(buyerID);
        if(buyer==null){
            throw new UserNotFound(String.format("user with id %s does'not exist",buyerID.toString()));
        }
        if(!buyer.getUserType().equals("BUYER")){
            throw new AccessNotFound(String.format("User with name %s does not have access to purchase product",buyer.getName()));
        }
        List <Product> products=new ArrayList<>();
        BillResponseBody billResponseBody=new BillResponseBody();
        billResponseBody.setProducts(new ArrayList<>());
        int totalAmount=0;
        int totalQuantity=0;
        AppOrder appOrder=new AppOrder();



        for(OrderProductRequestBody orderProduct:orderProducts){
            String productName= orderProduct.getProductName();
            int productQuantity= orderProduct.getQuantity();
             Product product=productRepositery.findByProductName(productName);
          if(product==null){
            throw new ProductNotFound(String.format("product with name %s not found",productName));
          }
          if(product.getQuantity()<productQuantity){
            throw new InvalidOperation(String.format("Product with name %s does not have enough quantity",productName));
          }
          int totalSoldQuantity=product.getTotalSoldQuantity()+productQuantity;
          int quantityLeft=product.getQuantity()-productQuantity;
         productRepositery.updateQuantity(quantityLeft,buyerID,totalSoldQuantity);

            SpecificProductOrderDetails productOrderDetails=new SpecificProductOrderDetails();
            productOrderDetails.setProductID(product.getId());
             productOrderDetails.setUnitprice(product.getPrice());
             productOrderDetails.setProductName(productName);
             productOrderDetails.setTotalPurchasedPrice(productQuantity*product.getPrice());
             totalAmount+=productQuantity*product.getPrice();
             totalQuantity+=productQuantity;
           billResponseBody.getProducts().add(productOrderDetails);

         products.add(product);

        }
          appOrder.setProduct(products);
          appOrder.setBuyer(buyer);
          appOrder.setTotalPrice(totalAmount);
          orderRepositery.save(appOrder);
          appOrder.setTotalQuantity(totalQuantity);


      billResponseBody.setTotalAmount(totalAmount);
             return billResponseBody;

    }




}
