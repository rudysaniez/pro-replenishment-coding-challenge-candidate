package com.adeo.pro.replenishment.coding.challenge.thread.expressyourself;

import java.util.Map;

public interface Message<T> {

	/**
	 * @return {@link Map the message header}
	 */
	public Map<String, Object> getHeaders();
	
	/**
	 * @return the payload
	 */
	public T getPayload();
}
