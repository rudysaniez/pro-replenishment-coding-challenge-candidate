package com.adeo.pro.replenishment.coding.challenge.thread.expressyourself;

import java.util.function.Consumer;

public interface BrokerConsumer<T> {
	
	/**
	 * @param consumer
	 */
	public void addConsumer(Consumer<T> consumer);
}
