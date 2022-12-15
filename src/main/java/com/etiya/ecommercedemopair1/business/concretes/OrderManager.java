package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.*;
import com.etiya.ecommercedemopair1.business.dtos.request.order.AddOrderRequest;
import com.etiya.ecommercedemopair1.business.dtos.request.productcategory.AddProductCartRequest;
import com.etiya.ecommercedemopair1.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.core.util.results.SuccessResult;
import com.etiya.ecommercedemopair1.entities.concretes.*;
import com.etiya.ecommercedemopair1.repository.abstracts.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;
    private CartService cartService;
    private ModelMapperService modelMapperService;
    private InvoiceService invoiceService;
    private AddressService addressService;

    @Autowired
    public OrderManager( OrderRepository orderRepository, ProductService productService, CartService cartService, ModelMapperService modelMapperService, InvoiceService invoiceService, @Lazy AddressService addressService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.cartService = cartService;
        this.modelMapperService = modelMapperService;
        this.invoiceService = invoiceService;
        this.addressService=addressService;

    }


    @Override
    @Transactional
    public Result addOrder(AddOrderRequest addOrderRequest) {
        checkProductAtCart(addOrderRequest.getCartId());
        checkAddressIfExists(addOrderRequest.getAddressId());
        List<Product> products=orderRepository.getProductsAtOrderWithCartId(addOrderRequest.getCartId());
        List<AddProductCartRequest> addProductCartRequests=new ArrayList<>();
        for(Product p:products)
        {
            addProductCartRequests.add(new AddProductCartRequest(p.getId(),addOrderRequest.getCartId()));
        }

        Order order = modelMapperService.getMapperforRequest().map(addOrderRequest, Order.class);

        List<ProductCart> productCarts=addProductCartRequests.stream().map(addProductCartRequest-> this.modelMapperService.getMapperforRequest().map(addProductCartRequest,ProductCart.class)).collect(Collectors.toList());

        Order savedOrder = this.orderRepository.save(order);

        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setTotalInvoicePrice(savedOrder.getTotalPrice());
        invoice.setOrder(savedOrder);
        invoice.getOrder().getCart().setProductCarts(productCarts);
        invoiceService.addInvoice(invoice);

        return new SuccessResult("Order was added successfully");

    }

    @Override
    public boolean existsByAddressId(int id) {
        return orderRepository.existByAddressId(id);
    }

    @Override
    public List<Product> getProductsAtOrderWithCartId(int identity) {
        return orderRepository.getProductsAtOrderWithCartId(identity);
    }


    public void checkProductAtCart(int id) {
        List<Product> products = cartService.getProductsWithCartId(id);
        if (products.size() == 0) {
            throw new BusinessException("There is no product in cart.Check your cart");
        }
    }
    public void checkAddressIfExists(int id)
    {
        boolean isExists=addressService.existsById(id);
        if(!isExists)
        {
            throw new BusinessException("This address could not be found");
        }

    }


}
