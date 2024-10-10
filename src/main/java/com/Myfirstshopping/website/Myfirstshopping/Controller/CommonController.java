package com.Myfirstshopping.website.Myfirstshopping.Controller;

import com.Myfirstshopping.website.Myfirstshopping.Service.CommonUserService;
import com.Myfirstshopping.website.Myfirstshopping.exception.UserNotFound;
import com.Myfirstshopping.website.Myfirstshopping.exception.WrongCredentialException;
import com.Myfirstshopping.website.Myfirstshopping.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CommonController {
    @Autowired
    CommonUserService commonUserService;

    @GetMapping("/login")
 public String authenticateUser(@RequestHeader String token){

        try{
            String result = commonUserService.authenticateUser(token);
            return result;
        }

        catch(WrongCredentialException wrongCredentialException){
         return wrongCredentialException.getMessage();
        }

        catch(UserNotFound userNotFound){
          return userNotFound.getMessage();
        }



 }
 @PostMapping("/register")
 public  String registerUser(@RequestBody AppUser appUser){

        commonUserService.registerUser(appUser);

     return "User saved successfully";


 }




}
