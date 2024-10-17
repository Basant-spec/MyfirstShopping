package com.Myfirstshopping.website.Myfirstshopping.ResponseBody;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillResponseBody {
    private List<SpecificProductOrderDetails> products;
    private int totalAmount;
}
