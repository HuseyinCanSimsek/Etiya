package com.etiya.ecommercedemopair1.business.dtos.request.order;

import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.business.dtos.request.productorder.SubProductOrderRequest;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class AddOrderRequest {

    private Date orderDate;
    private double totalPrice;
    private boolean isCompleted;
    private int productId;
    private int addressId;
    private int cartId;


    public AddOrderRequest(Date orderDate, double totalPrice, boolean isCompleted, int productId, int addressId, int cartId) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.isCompleted = isCompleted;
        this.productId = productId;
        this.addressId = addressId;
        this.cartId = cartId;
    }
}
