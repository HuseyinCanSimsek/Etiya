package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.request.customer.AddCustomerRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.cart.GetCartResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.customer.GetCustomerResponse;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.entities.concretes.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerService {
    DataResult<List<Customer>> getAll();
   DataResult<Customer> getById(int id);
//    Customer findCustomerWhereAddressId(int id);

    DataResult<String> findEmailByName(String name);
    DataResult<List<Customer>> getCustomerWithGender(String gender);

    Result addCustomer(AddCustomerRequest addCustomerRequest); // Sadece Request'li görelim.

    DataResult<GetCustomerResponse> addCustomerWithCustomerInfo(AddCustomerRequest addCustomerRequest); // Bir de Response'lu görelim.addCustomer gibi.
   DataResult<List<GetCustomerResponse>> getCustomerResponseWithGender(String gender);


    public Slice<Customer> findAllWithSlice(Pageable pageable);

}
