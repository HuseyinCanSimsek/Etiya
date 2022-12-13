package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.CartService;
import com.etiya.ecommercedemopair1.business.abstracts.InvoiceService;
import com.etiya.ecommercedemopair1.business.abstracts.OrderService;
import com.etiya.ecommercedemopair1.business.abstracts.ProductService;
import com.etiya.ecommercedemopair1.business.dtos.request.invoice.AddInvoiceRequest;
import com.etiya.ecommercedemopair1.business.dtos.request.order.AddOrderRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.order.GetOrderResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.core.util.results.SuccessResult;
import com.etiya.ecommercedemopair1.entities.concretes.Invoice;
import com.etiya.ecommercedemopair1.entities.concretes.Order;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import com.etiya.ecommercedemopair1.repository.abstracts.InvoiceRepository;
import com.etiya.ecommercedemopair1.repository.abstracts.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private ProductService productService;
    private CartService cartService;
    private ModelMapperService modelMapperService;
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public Result addOrder(AddOrderRequest addOrderRequest) {
        checkProductAtCart(addOrderRequest.getCartId());
        Order order=modelMapperService.getMapperforRequest().map(addOrderRequest,Order.class);


       Order savedOrder= orderRepository.save(order);
        AddInvoiceRequest addInvoiceRequest=new AddInvoiceRequest(LocalDateTime.now(), savedOrder.getTotalPrice(), savedOrder.getId());
        Invoice invoice=this.modelMapperService.getMapperforRequest().map(addInvoiceRequest,Invoice.class);

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
    public void checkProductAtCart(int id)
    {
        List<Product> products=cartService.getProductsWithCartId(id);
        if(products.size()==0)
        {
            throw new BusinessException("There is no product in cart.Check your cart");
        }
    }
}
