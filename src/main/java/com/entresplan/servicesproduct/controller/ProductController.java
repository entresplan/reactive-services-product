package com.entresplan.servicesproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entresplan.servicesproduct.dto.ProductDto;
import com.entresplan.servicesproduct.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class ProductController {
	
	@Autowired
	private ProductService service;

	@GetMapping
	public Flux<ProductDto> find(){
		return service.find();
	}
	
	@GetMapping("/{id}")
	public Mono<ProductDto> findById(@PathVariable String id){
		return service.findById(id);
	}
	
	@GetMapping("/search")
	public Flux<ProductDto> findByPrceRange(@RequestParam("min") double min, @RequestParam("max") double max){
		return service.findInPriceRange(min, max);
	}
	
	@PostMapping
	public Mono<ProductDto> save(@RequestBody Mono<ProductDto> productDtoMono){
		return service.save(productDtoMono);
	}
	
	@PutMapping("/{id}")
	public Mono<ProductDto> update(@PathVariable String id, Mono<ProductDto> productDtoMono){
		return service.update(productDtoMono, id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return service.delete(id);
	}
	
	
}
