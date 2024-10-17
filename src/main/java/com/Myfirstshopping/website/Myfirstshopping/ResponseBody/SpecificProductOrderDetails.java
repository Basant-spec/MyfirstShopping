package com.Myfirstshopping.website.Myfirstshopping.ResponseBody;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpecificProductOrderDetails {
    private UUID productID;
    private String productName;
    private int unitprice;
    private int totalPurchasedPrice;





}
