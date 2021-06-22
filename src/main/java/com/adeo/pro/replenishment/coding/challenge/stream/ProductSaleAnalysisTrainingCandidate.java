package com.adeo.pro.replenishment.coding.challenge.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductSaleAnalysisTrainingCandidate {

	/**
	 * The main.
	 * @param args
	 */
	public static void main(String[] args) {
		
		WebClient webClient = new WebClient();

		/*
		 * I wish a stream of products sorted by quantity (desc for FR and IT BUs).
		 * Note : No aggregation by product name.
		 */
		System.out.println(" > Sorted by quantity (desc) for FR and IT BUs :");
		
		webClient.getProductSale().
			forEach(System.out::println);
		
		System.out.println(" > -------------- ");
		
		
		/*
		 * I would like a stream of products sorted by the total amount (desc).
		 * For this case, all BUs are necessary.
		 * Note : No aggregation by product name.
		 */
		System.out.println(" > Sorted by the total amount (desc) for all BUs :");
		
		webClient.getProductSale().
			map(ProductSaleEnrichment::new).
			forEach(System.out::println);
		
		System.out.println(" > -------------- ");
		
		
		/*
		 * I would like a stream which contains the product with lowest sale.
		 * Note : No aggregation by product name.
		 */
		System.out.println(" > The product with lowest sale (lowest quantity) for all BUs :");
		
		Optional<ProductSale> lowestSale = webClient.getProductSale().
			findFirst();
		
		System.out.println(" > The product sale with the lowest sale is ".concat(lowestSale.get().toString()));
		System.out.println(" > -------------- ");
		
		
		/*
		 * I would like a stream which contains the product with the highest total amount.
		 * Note : No aggregation by product name.
		 */
		System.out.println(" > The product with highest total amount for all BUs :");
		
		Optional<ProductSaleEnrichment> highestTotalAmount = webClient.getProductSale().
			map(ProductSaleEnrichment::new).
			findFirst();
		
		System.out.println(" > The product sale with the highest total amount is ".concat(highestTotalAmount.get().toString()));
		System.out.println(" > -------------- ");
		
		
		/*
		 * I would like a stream which contains the product with the highest total amount.
		 * Note : An aggregation by product name is necessary.
		 */
		System.out.println(" > The product with highest total amount with an aggregation by product name :");
		
		webClient.getProductSale();
		
		System.out.println(" > -------------- ");
	}

	/*
	 * Your several methods.
	 */
	
	/*
	 * The embedded library.
	 */
	
	/**
	 * The WebClient which allows to send an http request to the API REST.
	 */
	public static final class WebClient {
		
		public static List<ProductSale> inMemory;
		
		public Stream<ProductSale> getProductSale() {
			
			List<ProductSale> data = new ArrayList<>();
			
			Arrays.asList(BusinessUnit.values()).
				forEach(bu -> {
			
						Arrays.asList(ProductName.values()).
							forEach(p -> data.add(ProductSale.
											builder().
											id(NonceService.getId()).
											name(p.name()).
											pricePerUnit(NonceService.getPrice(1)).
											businessUnit(bu.name()).
											quantity(NonceService.getQuantity()).
											build())
							);
					});
		
			if(inMemory == null) inMemory = data;
			
			return inMemory.
					stream();
		}
	}
	
	/**
	 * Extend the {@link ProductSale} and I enrich this class to manage the total amount.
	 */
	@Getter @Setter @ToString(callSuper = true)
	public static final class ProductSaleEnrichment extends ProductSale {
		
		private Double totalAmount;
		
		public ProductSaleEnrichment(ProductSale productSale) {
			super(productSale.getId(), productSale.getName(), productSale.getQuantity(), productSale.getPricePerUnit(), productSale.getBusinessUnit());
		}
	}
	
	public static enum BusinessUnit {
		
		FR,
		IT,
		ES,
		PT;
	}
	
	public static enum ProductName {
		
		Driller,
		Hammer,
		Screwdriver,
		MultiPlug,
		Chainsaw;
	}
}
