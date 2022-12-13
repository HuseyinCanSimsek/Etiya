package com.etiya.ecommercedemopair1.business.dtos.response.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPaymentResponse {
    private int Id;
    private int orderId;
    private boolean isVerified;

    private int paymentTypeId;
}
