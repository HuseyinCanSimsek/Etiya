package com.etiya.ecommercedemopair1.business.dtos.response.productcart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductCartResponse {
    private int productId;
    private int cartId;
}
