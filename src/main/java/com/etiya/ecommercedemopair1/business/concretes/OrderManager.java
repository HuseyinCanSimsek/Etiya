package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.CartService;
import com.etiya.ecommercedemopair1.business.abstracts.OrderService;
import com.etiya.ecommercedemopair1.business.abstracts.ProductService;
import com.etiya.ecommercedemopair1.business.dtos.request.order.AddOrderRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.core.util.results.SuccessResult;
import com.etiya.ecommercedemopair1.entities.concretes.Order;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import com.etiya.ecommercedemopair1.repository.abstracts.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private ProductService productService;
    private CartService cartService;
    private ModelMapperService modelMapperService;

    @Override

    public Result addOrder(AddOrderRequest addOrderRequest) {
        checkProductAtCart(addOrderRequest.getProductId());
        Order order=modelMapperService.getMapperforRequest().map(addOrderRequest,Order.class);

       Order savedOrder= orderRepository.save(order);
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
