package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Order;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select case when count(o)> 0 then true else false end from Order as o inner join Address as a on o.address=a where a.id=:id")
    boolean existByAddressId(int id);
    @Query("select distinct p from Product as p inner join ProductCart as pc on " +
            "p=pc.product inner join Cart as c on pc.cart=c " +
            "inner join Order as o on o.cart=pc.cart where c.id in(:identity) ")
    List<Product> getProductsAtOrderWithCartId(int identity);
}
