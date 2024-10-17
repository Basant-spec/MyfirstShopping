package com.Myfirstshopping.website.Myfirstshopping.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}
