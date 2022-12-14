package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.response.cart.GetCartResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.customer.GetCustomerResponse;
import com.etiya.ecommercedemopair1.entities.concretes.Address;
import com.etiya.ecommercedemopair1.entities.concretes.Cart;
import com.etiya.ecommercedemopair1.entities.concretes.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    String findEmailByName(String name);
    @Query("select c from Customer c where c.gender=:gender")
    List<Customer> getCustomerWithGender(String gender);
    @Query("select new com.etiya.ecommercedemopair1.business.dtos.response.customer.GetCustomerResponse(c.id,c.email,c.name,c.phone,c.birth_date,c.gender) from Customer as c where c.gender=:gender " +
            "group by c.id,c.email,c.name,c.phone,c.birth_date,c.gender")
   List<GetCustomerResponse> getCustomerResponseWithGender(String gender);


//    @Query("Select c from Address as a join a.User as u join u.Customer as c Where a.id=:id")
//    Customer findCustomerWhereAddressId(@Param("id") int id);

//@Query("Select c from Customer c JOIN c.User u on u.id=c.id where u.name=:name")
//String getEmailByCustomerName(@Param("name") String name);

    @Query("select c from Customer as c")
    Slice<Customer> findAllWithSlice(Pageable pageable);


}

