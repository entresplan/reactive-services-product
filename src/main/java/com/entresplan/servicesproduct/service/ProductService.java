package com.entresplan.servicesproduct.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	public Mono<ProductDto> save( Mono<ProductDto> productDtoMono){
	//	logger.info("test", productDtoMono.subscribe(data -> System.out.println(data)));
		return productDtoMono.map(AppUtils::dtoToEntity)
				.doOnNext(System.out::println)
						.flatMap(repository::insert)
						.doOnNext(e -> {
							logger.info("Product name: {}",e.getName());
						})
						.map(AppUtils::entityToDto)
						.doOnSuccess(e->{
							logger.info("Dto code: {}",  e.getCode() );
						});
		
	}
	
	public Mono<ProductDto> update(Mono<ProductDto> productDtoMono, String id){
		return repository.findById(id)
				.flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity)
				.doOnNext(e -> e.setId(id)))
				.flatMap(repository::save)
				.map(AppUtils::entityToDto);		
	}
	
	public Mono<Void> delete(String id){
		return repository.deleteById(id);
	}

}
