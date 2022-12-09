package com.etiya.ecommercedemopair1.business.dtos.request.payment;

import com.etiya.ecommercedemopair1.entities.concretes.Order;
import com.etiya.ecommercedemopair1.entities.concretes.PaymentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class AddPaymentRequest {

    private int orderId;
    private boolean isVerified;
    private int paymentTypeId;
}
