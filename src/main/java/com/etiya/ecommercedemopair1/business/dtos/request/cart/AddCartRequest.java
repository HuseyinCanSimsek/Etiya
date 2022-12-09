package com.etiya.ecommercedemopair1.business.dtos.request.cart;

import com.etiya.ecommercedemopair1.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AddCartRequest {

    private double totalPrice;
    private int customerId;

}
