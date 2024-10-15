package com.Myfirstshopping.website.Myfirstshopping.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseBody {

    private String productName;
    private int price;
    private int quantity;
    private Double rating;
    private String category;
    private UserResponseBody userResponseBody;

}
