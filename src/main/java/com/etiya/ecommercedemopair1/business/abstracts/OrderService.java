package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.request.order.AddOrderRequest;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.entities.concretes.Product;

import java.util.List;

public interface OrderService {

    Result addOrder(AddOrderRequest addOrderRequest);
    boolean existsByAddressId(int id);
    List<Product> getProductsAtOrderWithCartId(int identity);
}
