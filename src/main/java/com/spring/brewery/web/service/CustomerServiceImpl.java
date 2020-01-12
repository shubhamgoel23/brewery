package com.spring.brewery.web.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spring.brewery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		
		return CustomerDto.builder().id(UUID.randomUUID())
				.name("Shubham")
				.build();
	}

}
