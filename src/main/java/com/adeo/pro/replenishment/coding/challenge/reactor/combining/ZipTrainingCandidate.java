package com.adeo.pro.replenishment.coding.challenge.reactor.combining;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;

public class ZipTrainingCandidate {

	/**
	 * The main.
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * I would like you to retrieve the flow of products.
		 * You can use the WebClient component which is available.
		 */
		Flux<Product> products = Flux.empty();
		
		/*
		 * I would like you to retrieve the flow of products sale.
		 * You can use the WebClient component which is available.
		 */
		Flux<ProductSale> productsSale = Flux.empty();
		
		/*
		 * I would like you to perform an aggregation flow.
		 */
		Flux<ProductSaleEnrichment> productsSaleAggregation = Flux.empty();
		
		System.out.println(" > -------------- ");
	}
	
	/*
	 * Your several methods.
	 */
	
	/*
	 * The embedded library.
	 */
	
	/**
	 * The WebClient component.
	 */
	@Getter @AllArgsConstructor
	public static class WebClient {
		
		static WebClient getInstance() {
			return new WebClient();
		}
		
		/**
		 * @return {@link Product flux of products}
		 */
		public Flux<Product> getFluxOfProductsOrderByIdentifier() {
			return Flux.fromStream(generateProduct(10));
		}
		 
		/**
		 * @return {@link ProductSale flux of products sale}
		 */
		public Flux<ProductSale> getFluxOfProductsSaleOrderByIdentifier() {
			return Flux.fromStream(generateProductSale(10));
		}
		
		/**
		 * @param limit
		 * @return {@link Product list of product}
		 */
		private Stream<Product> generateProduct(final Integer limit) {
			
			return Stream.iterate(1, i -> i < limit, i -> ++i).
				map(id -> new Product(id, randomProductName(id)));
		}
		
		/**
		 * @param limit
		 * @return {@link ProductSale list of product sale}
		 */
		private Stream<ProductSale> generateProductSale(final Integer limit) {
			
			return Stream.iterate(1, i -> i < limit, i -> ++i).
				map(id -> new ProductSale(id, new Random().nextInt(99), 
						new BigDecimal(new Random().nextDouble()).
							multiply(new BigDecimal(new Random().nextInt(9999))).setScale(2, RoundingMode.HALF_UP).doubleValue()));
		}
		
		/**
		 * @param id
		 * @return {@link String the product name}
		 */
		private String randomProductName(Integer id) {
			
			return ProductName.values()[new Random().nextInt(ProductName.values().length - 1)].
					name().
					concat(" v").
					concat(id.toString());
		}
		
	}
	
	@Getter @AllArgsConstructor @ToString
	public static class Product {
		
		private final Integer id;
		private final String name;
	}
	
	@Getter @AllArgsConstructor @ToString
	public static class ProductSale {
		
		private final Integer id;
		private final Integer quantity;
		private final Double totalAmount;
	}
	
	@Getter @AllArgsConstructor @ToString
	public static class ProductSaleEnrichment {
		
		private final Integer id;
		private final String name;
		private final Integer quantity;
		private final Double totalAmount;
	}
	
	public static enum ProductName {
		
		Driller,
		Hammer,
		Screwdriver,
		MultiPlug,
		Chainsaw;
	}
}
