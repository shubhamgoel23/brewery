package com.spring.brewery.web.mapper;

import org.mapstruct.Mapper;

import com.spring.brewery.domain.Customer;
import com.spring.brewery.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {

	CustomerDto customerToCustomerDto(Customer beer);

	Customer customerDtoToCustomer(CustomerDto beerDto);

}
