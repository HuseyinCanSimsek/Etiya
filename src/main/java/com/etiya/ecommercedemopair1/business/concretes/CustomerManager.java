package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.CustomerService;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.dtos.request.customer.AddCustomerRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.cart.GetCartResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.customer.GetCustomerResponse;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.messages.MessageManager;
import com.etiya.ecommercedemopair1.core.util.messages.MessageService;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.core.util.results.SuccessDataResult;
import com.etiya.ecommercedemopair1.core.util.results.SuccessResult;
import com.etiya.ecommercedemopair1.entities.concretes.Customer;
import com.etiya.ecommercedemopair1.repository.abstracts.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private MessageService messageService;


    @Override
    public DataResult<List<Customer>> getAll() {
        List<Customer>customers=customerRepository.findAll();
        return new SuccessDataResult<List<Customer>>("All customers were called",customers);
    }

    @Override
    public DataResult<Customer> getById(int id) {
        Customer customer= customerRepository.findById(id).orElseThrow();
        return new SuccessDataResult<Customer>("Customer was found by given id");
    }

    @Override
    public DataResult<String> findEmailByName(String name) {

        String email= this.customerRepository.findEmailByName(name);
        return new SuccessDataResult<String>("Related E Mail address was found by customer name",email);
    }

    @Override
    public DataResult<List<Customer>> getCustomerWithGender(String gender) {
        List<Customer> customers=customerRepository.getCustomerWithGender(gender);
        return new SuccessDataResult<List<Customer>>("Customers were found according to gender",customers);
    }

    @Override
    public Result addCustomer(AddCustomerRequest addCustomerRequest) {
        // this.customerRepository.save(customer);

        // Mapping
        Customer customer = modelMapperService.getMapperforRequest().map(addCustomerRequest, Customer.class);
        Customer customer1=customerRepository.save(customer);
      return new SuccessResult(true,"Customer was added successfully");

    }

    @Override
    public DataResult<GetCustomerResponse> addCustomerWithCustomerInfo(AddCustomerRequest addCustomerRequest) {
        // return this.customerRepository.save(addCustomerRequest);
        // Mapping
        Customer customer = modelMapperService.getMapperforRequest().map(addCustomerRequest, Customer.class);

        Customer savedCustomer = customerRepository.save(customer);

        GetCustomerResponse getCustomerResponse = modelMapperService.getMapperforResponse().map(savedCustomer,GetCustomerResponse.class);

        return new SuccessDataResult<GetCustomerResponse>("Customer was added successfully",getCustomerResponse);
    }

    @Override
    public DataResult<List<GetCustomerResponse>> getCustomerResponseWithGender(String gender) {
        List<GetCustomerResponse> customer= customerRepository.getCustomerResponseWithGender(gender);
        return new SuccessDataResult<List<GetCustomerResponse>>("Customers were found according to themselves gender",customer);
    }

    @Override
    public Slice<Customer> findAllWithSlice(Pageable pageable) {
        return this.customerRepository.findAllWithSlice(pageable);
    }
}
