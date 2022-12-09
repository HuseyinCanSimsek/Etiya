package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();
}
