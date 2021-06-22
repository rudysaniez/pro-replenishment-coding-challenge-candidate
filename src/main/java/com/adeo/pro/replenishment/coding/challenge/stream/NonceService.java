package com.adeo.pro.replenishment.coding.challenge.stream;

import java.math.BigDecimal;
import java.util.Random;

public class NonceService {

	private static final Random random = new Random();
	public static final String DEFAULT_SEPARATOR = "_";
	public static final String FINAL_SEPARATOR = "@";
	
	private static final String ID_FORMAT = "00000000";
	private static final Integer ID_MAX = 99999999;
	
	/**
	 * @return supplierId generated
	 */
	public static String getId() {
		
		String baseId = ID_FORMAT.concat(String.valueOf(random.nextInt(ID_MAX)));
		return baseId.substring(baseId.length() - ID_FORMAT.length());
	}
	
	/**
	 * @return a quantity
	 */
	public static Integer getQuantity() {
		return random.nextInt(99);
	}
	
	/**
	 * @return a suffix.
	 */
	public static Integer getSuffix() {
		return getQuantity();
	}
	
	/**
	 * @param quantity
	 * @return the price
	 */
	public static Double getPrice(Integer quantity) {
		
		Integer i = random.nextInt(999);
		
		return new BigDecimal(i.doubleValue()).multiply(new BigDecimal(quantity.doubleValue())).
				doubleValue();
	}
}
