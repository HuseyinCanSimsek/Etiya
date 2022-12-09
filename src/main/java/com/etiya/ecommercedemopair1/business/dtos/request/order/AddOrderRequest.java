package com.etiya.ecommercedemopair1.business.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
public class AddOrderRequest {

    private Date orderDate;
    private String paymentMethod;
    private boolean isCompleted;

}
