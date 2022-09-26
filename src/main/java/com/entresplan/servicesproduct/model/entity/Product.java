package com.entresplan.servicesproduct.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="products")
public class Product {
	
	@Id
	private String id;
	private String code;
	private String name;
	private int qty;
	private double price;

}
