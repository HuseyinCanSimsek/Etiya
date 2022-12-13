package com.etiya.ecommercedemopair1.business.dtos.request.order;

import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.business.dtos.request.productorder.SubProductOrderRequest;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
public class AddOrderRequest {

    private Date orderDate;
    private double totalPrice;
    private boolean isCompleted;
    private int productId;
    private int addressId;
    private int cartId;
}
