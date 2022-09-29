package com.entresplan.servicesproduct.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private String id;
	private String code;
	private String name;
	private int qty;
	private double price;
	
	

}
