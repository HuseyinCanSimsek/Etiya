package com.etiya.ecommercedemopair1.api.controllers;

import com.etiya.ecommercedemopair1.business.abstracts.OrderService;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.constants.Paths;
import com.etiya.ecommercedemopair1.business.dtos.request.order.AddOrderRequest;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix+"orders")
public class OrdersController {
    private OrderService orderService;
    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/getProductsOrderWithCartId")
    public List<Product> getProductsOrderWithCartId(int id)
    {
        return  orderService.getProductsAtOrderWithCartId(id);
    }

    @PostMapping("/addOrder")
    Result addOrder(@RequestBody @Valid AddOrderRequest addOrderRequest)
    {
        return orderService.addOrder(addOrderRequest);
    }
}
