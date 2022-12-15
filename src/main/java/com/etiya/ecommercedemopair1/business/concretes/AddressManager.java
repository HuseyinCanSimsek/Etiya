package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.*;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.dtos.request.address.AddAddressRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.address.GetAddressResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.order.GetOrderResponse;
import com.etiya.ecommercedemopair1.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.messages.MessageService;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.core.util.results.SuccessDataResult;
import com.etiya.ecommercedemopair1.core.util.results.SuccessResult;
import com.etiya.ecommercedemopair1.entities.concretes.*;
import com.etiya.ecommercedemopair1.repository.abstracts.AddressRepository;
import com.etiya.ecommercedemopair1.repository.abstracts.CityRepository;
import com.etiya.ecommercedemopair1.repository.abstracts.CountryRepository;
import com.etiya.ecommercedemopair1.repository.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressManager implements AddressService {

    private AddressRepository addressRepository;
    private UserService userService;
    private CityService cityService;
    private CountryService countryService;
    private OrderService orderService;
    private final ModelMapperService modelMapperService;
    private MessageService messageService;






    @Override
    public DataResult<Address> getById(int id) {
        Address address=addressRepository.findById(id).orElseThrow();
        return new SuccessDataResult<Address>(messageService.getMessage(Messages.Address.addressWasFound),address);
    }

    @Override
    public DataResult<List<Address>> getAll() {
        List<Address> addresses=this.addressRepository.findAll();
        return  new SuccessDataResult<List<Address>>(messageService.getMessage(Messages.Address.getAllAddresses),addresses);
    }

    @Override
    public Result addAddress(Address address) {
        Address address1=this.addressRepository.save(address);
        return new SuccessResult(messageService.getMessage(Messages.Address.addressSuccessAdded));
    }

    @Override
    public Result addAddressInfo(AddAddressRequest addAddressRequest) {
        // Mapping - > map the attributes from the request to the attributes of the object we created ourselves.
        Address address = modelMapperService.getMapperforRequest().map(addAddressRequest,Address.class);

        //address.getUser().setId(addAddressRequest.getUser());
        //address.getCity().setId(addAddressRequest.getCity());
        //address.getCountry().setId(addAddressRequest.getCountry());


       Address address1= this.addressRepository.save(address);
       return new SuccessResult(messageService.getMessage(Messages.Address.addressSuccessAdded));


    }

    @Override
    public DataResult<GetAddressResponse> getAddressWithInfo(AddAddressRequest addAddressRequest) {
        Address address =modelMapperService.getMapperforRequest().map(addAddressRequest,Address.class);

        checkUserExists(addAddressRequest.getUserId());
        checkCityExists(addAddressRequest.getCityId());
        Address savedAddress = addressRepository.save(address);

        GetAddressResponse getAddressResponse = modelMapperService.getMapperforResponse().map(savedAddress,GetAddressResponse.class);

        return new SuccessDataResult<GetAddressResponse>(messageService.getMessage(Messages.Address.addressSuccessAdded),getAddressResponse);
    }

    @Override
    public DataResult<List<GetOrderResponse>> getOrderWithAddressId(int id) {

        List<GetOrderResponse>  orderResponses=addressRepository.getOrderWithAddressId(id);
        checkOrderExistsAddressId(id);
      return new SuccessDataResult<List<GetOrderResponse>>(messageService.getMessage(Messages.Address.ordersListedByAddressId),orderResponses);
    }

    @Override
    public boolean existsById(int id) {
        return addressRepository.existsById(id);
    }

    private void checkUserExists(int id) {

        boolean isExist = userService.existsById(id);
        if (!isExist) {
            throw new BusinessException(messageService.getMessage(Messages.User.userExists));
        }
    }

    private void checkCityExists(int id) {

        boolean isExist = cityService.existsById(id);
        if (!isExist) {
            throw new BusinessException(messageService.getMessage(Messages.City.cityExists));
        }
    }

    private void checkCountryExists(int id) {

        boolean isExist = countryService.existsById(id);
        if (!isExist) {
            throw new BusinessException(Messages.Country.countryExists);
        }
    }
    private void checkOrderExistsAddressId(int id)
    {
        boolean isExist = orderService.existsByAddressId(id);
        if (!isExist) {
            throw new BusinessException(messageService.getMessage(Messages.Order.orderExists));
        }

    }
}
