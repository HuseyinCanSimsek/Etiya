package com.etiya.ecommercedemopair1.business.dtos.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GetOrderResponse {
    private int id;
    private Date orderDate;
    private double totalPrice;
    private boolean isCompleted;
    private int productId;
    private int addressId;
    private int cartId;

    public GetOrderResponse(int id, Date orderDate, double totalPrice, boolean isCompleted) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.isCompleted = isCompleted;
    }
}
