package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.OrderService;
import com.etiya.ecommercedemopair1.repository.abstracts.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public boolean existsByAddressId(int id) {
        return orderRepository.existByAddressId(id);
    }
}
