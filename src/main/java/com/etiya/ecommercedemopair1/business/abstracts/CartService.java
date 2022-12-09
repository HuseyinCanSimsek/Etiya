package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
}
