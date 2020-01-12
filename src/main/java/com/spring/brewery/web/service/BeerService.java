package com.spring.brewery.web.service;

import java.util.UUID;

import com.spring.brewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

}
