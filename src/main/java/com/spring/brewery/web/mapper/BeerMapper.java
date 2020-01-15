package com.spring.brewery.web.mapper;

import org.mapstruct.Mapper;

import com.spring.brewery.domain.Beer;
import com.spring.brewery.web.model.BeerDto;

@Mapper
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);
	
	Beer beerDtoToBeer(BeerDto beerDto);

}
