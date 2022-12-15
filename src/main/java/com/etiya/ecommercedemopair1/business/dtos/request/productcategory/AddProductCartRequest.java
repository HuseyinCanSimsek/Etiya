package com.etiya.ecommercedemopair1.business.dtos.request.productcategory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddProductCartRequest {
    private int productId;
    private int cartId;

}
