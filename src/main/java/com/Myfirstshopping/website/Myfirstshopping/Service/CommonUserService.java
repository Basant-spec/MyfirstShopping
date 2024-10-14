package com.Myfirstshopping.website.Myfirstshopping.Service;

import com.Myfirstshopping.website.Myfirstshopping.Repositery.UserRepositery;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.WrongCredentialException;
import com.Myfirstshopping.website.Myfirstshopping.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUserService {
      @Autowired
     private UserRepositery userRepositery;
    public String authenticateUser(String token){
        //in this we will split the token to get email and pasword diff
        String []  userCredentials = token.split(":");


        String userEmail=userCredentials[0];
        String userPassword = userCredentials[1];

        AppUser user=userRepositery.findByEmail(userEmail);
        if(user==null){
            throw new UserNotFound(String.format("User with email %s does not exist in system.",userEmail));

        }

        String originalPassword=user.getPassword();

        if(originalPassword.equals(userPassword)){

            return "Welcome, Your login is successful";
        }

        throw new WrongCredentialException("Password is wrong");

    }


    public AppUser getUserById(UUID userId){

       AppUser user=userRepositery.findById(userId).orElse(null);

      return user;
    }

    public Boolean isSeller(UUID sellerID){
     AppUser user=getUserById(sellerID);
        if(user==null){
      return null;
        }

       return user.getUserType().equals("SELLER");


    }
    public void registerUser(AppUser user){

        userRepositery.save(user);


    }



}
