package com.Myfirstshopping.website.Myfirstshopping.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseBody {
    private String name;
    private int age;
    private String email;
    private Long phoneNumber;
    private String address;
}
