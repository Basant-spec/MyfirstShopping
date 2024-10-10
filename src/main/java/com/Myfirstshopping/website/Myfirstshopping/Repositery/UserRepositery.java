package com.Myfirstshopping.website.Myfirstshopping.Repositery;


import com.Myfirstshopping.website.Myfirstshopping.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepositery extends JpaRepository<AppUser, UUID>{

public AppUser findByEmail(String email);

}
