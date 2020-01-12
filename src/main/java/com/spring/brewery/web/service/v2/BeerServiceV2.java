package com.spring.brewery.web.service.v2;

import java.util.UUID;

import com.spring.brewery.web.model.v2.BeerDtoV2;

public interface BeerServiceV2 {

	BeerDtoV2 getBeerById(UUID beerId);

	BeerDtoV2 saveNewBeer(BeerDtoV2 beerDtoV2);

	void updateBeer(UUID beerId, BeerDtoV2 beerDtoV2);

	void deleteById(UUID beerId);


}
