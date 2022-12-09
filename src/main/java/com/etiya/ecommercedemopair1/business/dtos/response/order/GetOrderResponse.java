package com.etiya.ecommercedemopair1.business.dtos.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GetOrderResponse {
    private int id;
    private Date orderDate;
    private String paymentMethod;
    private boolean isCompleted;
}
