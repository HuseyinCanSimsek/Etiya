package com.etiya.ecommercedemopair1.business.dtos.request.paymenttype;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class AddPaymentTypeRequest {

    private String typeName;
    private String description;

}
