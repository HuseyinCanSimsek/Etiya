package com.etiya.ecommercedemopair1.business.dtos.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCartResponse {
    private int id;
    private double totalPrice;

}
