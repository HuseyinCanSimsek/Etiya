package com.etiya.ecommercedemopair1.api.controllers;

import com.etiya.ecommercedemopair1.business.abstracts.CustomerService;
import com.etiya.ecommercedemopair1.business.constants.Paths;
import com.etiya.ecommercedemopair1.business.dtos.request.customer.AddCustomerRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.cart.GetCartResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.customer.GetCustomerResponse;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(Paths.apiPrefix + "customers")
@RestController
public class CustomersController {
    private final CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Customer>> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Customer> getById(@PathVariable int id) {
        return this.customerService.getById(id);
    }

    @GetMapping("/getEmail")
    public DataResult<String> findEmailByName(@RequestParam("name") String name) {
        return this.customerService.findEmailByName(name);
    }

    @GetMapping("/getCustomerByGender")
    public DataResult<List<Customer>> getCustomerWithGender(@RequestParam("gender") String gender) {
        return this.customerService.getCustomerWithGender(gender);
    }

    @PostMapping("/addCustomer")
    public Result addCustomer(@RequestBody AddCustomerRequest addCustomerRequest) {
        return customerService.addCustomer(addCustomerRequest);
    }

    @PostMapping("/addCustomerWithCustomerInfo")
    public ResponseEntity<DataResult<GetCustomerResponse>> addCustomerWithCustomerInfo(@RequestBody AddCustomerRequest addCustomerRequest) {

        return new ResponseEntity<DataResult<GetCustomerResponse>>(customerService.addCustomerWithCustomerInfo(addCustomerRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/getCustomerResponseWithGender")
    public @ResponseBody DataResult<List<GetCustomerResponse>> getCustomerResponsesWithGender(@RequestParam("gender") String gender) {
        return customerService.getCustomerResponseWithGender(gender);
    }

    @GetMapping("/getAllWithSlice")
    public Slice<Customer> getAllWithSlice(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerService.findAllWithSlice(pageable);

    }


}

