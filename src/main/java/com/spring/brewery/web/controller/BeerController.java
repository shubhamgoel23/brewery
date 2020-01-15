package com.spring.brewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.spring.brewery.web.model.BeerDto;
import com.spring.brewery.web.service.BeerService;

@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
@Validated
public class BeerController {

	private final BeerService beerService;

	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable("beerId") UUID beerId) {

		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<String> handlePost(@Valid @RequestBody BeerDto beerDto) {
		BeerDto savedDto = beerService.saveNewBeer(beerDto);
		
		HttpHeaders headers = new HttpHeaders();
		//ToDo add hostname to url
		headers.add("Location", "/api/v1/beer/"+savedDto.getId().toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<String> handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
		
		beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex){
		List<String> errors = new ArrayList<String>(ex.getConstraintViolations().size());
		
		ex.getConstraintViolations().forEach(constraintVioltion->{
			errors.add(constraintVioltion.getPropertyPath()+" : "+constraintVioltion.getMessage());
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> validationErrorHandler(MethodArgumentTypeMismatchException ex){
		
		
		return new ResponseEntity<>(ex.getCause().getMessage(),HttpStatus.BAD_REQUEST);
		
	}

}
