package com.Myfirstshopping.website.Myfirstshopping.requestBody;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductRequestBody {
  private String productName;
  private int quantity;

}
