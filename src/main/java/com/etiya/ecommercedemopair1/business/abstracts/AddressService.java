package com.etiya.ecommercedemopair1.business.abstracts;


import com.etiya.ecommercedemopair1.business.dtos.request.address.AddAddressRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.address.GetAddressResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.order.GetOrderResponse;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.entities.concretes.Address;

import java.util.List;

public interface AddressService {


    DataResult<Address> getById(int id);
    DataResult<List<Address>> getAll();

    Result addAddress(Address address);

    Result addAddressInfo(AddAddressRequest addAddressRequest);

    DataResult<GetAddressResponse> getAddressWithInfo(AddAddressRequest addAddressRequest);
    DataResult<List<GetOrderResponse>> getOrderWithAddressId(int id);

}
