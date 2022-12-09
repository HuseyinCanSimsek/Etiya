package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select case when count(o)> 0 then true else false end from Order as o inner join Address as a on o.address=a where a.id=:id")
    boolean existByAddressId(int id);
}
