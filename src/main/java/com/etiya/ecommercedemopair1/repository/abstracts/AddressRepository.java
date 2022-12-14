package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.response.order.GetOrderResponse;
import com.etiya.ecommercedemopair1.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;



@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("select new com.etiya.ecommercedemopair1.business.dtos.response.order.GetOrderResponse(o.id,o.orderDate,o.totalPrice,o.isCompleted) from Order" +
            " as o inner join Address as a" +
            " on o.address=a where a.id in(:identity) " +
            "group by o.id,o.orderDate,o.totalPrice,o.isCompleted")
    List<GetOrderResponse> getOrderWithAddressId(int identity);
    boolean existsById(int id);

}
