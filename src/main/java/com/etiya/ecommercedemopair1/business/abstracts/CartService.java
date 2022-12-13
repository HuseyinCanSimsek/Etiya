package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Cart;
import com.etiya.ecommercedemopair1.entities.concretes.Product;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
    List<Product> getProductsWithCartId(int identity);
}
