package com.etiya.ecommercedemopair1.business.dtos.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class GetOrderResponse {
    private int id;
    private boolean isCompleted;


    public GetOrderResponse(int id, boolean isCompleted) {
        this.id = id;
        this.isCompleted = true;
    }
}
