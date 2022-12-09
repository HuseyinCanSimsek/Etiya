package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.*;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.dtos.request.address.AddAddressRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.address.GetAddressResponse;
import com.etiya.ecommercedemopair1.business.dtos.response.order.GetOrderResponse;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
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




    @Override
    public List<Address> getAddressesByCityName(String name) {
        return null;
    }

    @Override
    public Address getById(int id) {
        return null;
    }

    @Override
    public List<Address> getAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public void addAddress(Address address) {
        //this.addressRepository.save(address);
    }

    @Override
    public void addAddressInfo(AddAddressRequest addAddressRequest) {
        // Mapping - > map the attributes from the request to the attributes of the object we created ourselves.
        Address address = modelMapperService.getMapperforRequest().map(addAddressRequest,Address.class);

        //address.getUser().setId(addAddressRequest.getUser());
        //address.getCity().setId(addAddressRequest.getCity());
        //address.getCountry().setId(addAddressRequest.getCountry());


        this.addressRepository.save(address);


    }

    @Override
    public GetAddressResponse getAddressWithInfo(AddAddressRequest addAddressRequest) {
        Address address =modelMapperService.getMapperforRequest().map(addAddressRequest,Address.class);

        checkUserExists(addAddressRequest.getUserId());
        checkCountryExists(addAddressRequest.getCountryId());
        checkCityExists(addAddressRequest.getCityId());
        Address savedAddress = addressRepository.save(address);

        GetAddressResponse getAddressResponse = modelMapperService.getMapperforResponse().map(savedAddress,GetAddressResponse.class);

        return getAddressResponse;
    }

    @Override
    public List<GetOrderResponse> getOrderWithAddressId(int id) {
        return addressRepository.getOrderWithAddressId(id);
    }

    private void checkUserExists(int id) {

        boolean isExist = userService.existsById(id);
        if (!isExist) {
            throw new RuntimeException(Messages.User.userExists);
        }
    }

    private void checkCityExists(int id) {

        boolean isExist = cityService.existsById(id);
        if (!isExist) {
            throw new RuntimeException(Messages.City.cityExists);
        }
    }

    private void checkCountryExists(int id) {

        boolean isExist = countryService.existsById(id);
        if (!isExist) {
            throw new RuntimeException(Messages.Country.countryExists);
        }
    }
    private void checkOrderExistsAddressId(int id)
    {
        boolean isExist = orderService.existsByAddressId(id);
        if (!isExist) {
            throw new RuntimeException(Messages.Country.countryExists);
        }

    }
}
