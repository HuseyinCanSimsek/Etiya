package com.etiya.ecommercedemopair1.business.dtos.response.paymenttype;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPaymentTypeResponse {
    private int Id;
    private String typeName;
    private String description;
}
