package com.entresplan.servicesproduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.entresplan.servicesproduct.dto.ProductDto;
import com.entresplan.servicesproduct.repository.ProductRepository;
import com.entresplan.servicesproduct.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	
	public Flux<ProductDto> find(){
		return repository.findAll().map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto> findById(String id){
		return repository.findById(id).map(AppUtils::entityToDto);
	}
	
	
	public Flux<ProductDto> findInPriceRange(double min, double max){
		return repository.findByPriceBetween(Range.closed(min, max));
	}
	
	
	

}
