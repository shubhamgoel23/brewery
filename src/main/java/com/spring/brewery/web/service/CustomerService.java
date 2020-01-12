package com.spring.brewery.web.service;

import java.util.UUID;

import com.spring.brewery.web.model.CustomerDto;

public interface CustomerService {

	CustomerDto getCustomerById(UUID customerId);

}
