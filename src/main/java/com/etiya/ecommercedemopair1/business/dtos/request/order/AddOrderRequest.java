package com.etiya.ecommercedemopair1.business.dtos.request.order;

import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class AddOrderRequest {



   @Min(value = 0,message = "Choose your order address")
    private int addressId;
    private int cartId;


    public AddOrderRequest(int addressId, int cartId) {
        this.addressId = addressId;
        this.cartId = cartId;
    }
}
