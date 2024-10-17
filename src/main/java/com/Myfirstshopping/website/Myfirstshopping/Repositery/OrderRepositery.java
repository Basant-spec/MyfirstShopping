package com.Myfirstshopping.website.Myfirstshopping.Repositery;

import com.Myfirstshopping.website.Myfirstshopping.model.AppOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepositery extends JpaRepository<AppOrder, UUID> {



}
