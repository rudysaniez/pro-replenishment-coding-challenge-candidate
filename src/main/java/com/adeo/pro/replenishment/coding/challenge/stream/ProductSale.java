package com.adeo.pro.replenishment.coding.challenge.stream;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data @AllArgsConstructor @Builder
public class ProductSale {

	@NotBlank
	private String id;
	
	@NotBlank @Exclude
	private String name;
	
	@NotNull @Exclude
	private Integer quantity;
	
	@NotNull @Exclude
	private Double pricePerUnit;
	
	@NotBlank
	private String businessUnit;
}
