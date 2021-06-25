package com.adeo.pro.replenishment.coding.challenge.thread.expressyourself;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

@ToString
public class GenericMessage<T> implements Message<T>, Serializable {

	private static final long serialVersionUID = 1L;
	protected final static String HEADER_SOURCE = "SOURCE";
	
	private final T payload;
	private final Map<String, Object> headers;
	
	public GenericMessage(T payload, String source) {
		
		this.payload = payload;
		this.headers = new HashMap<>();
		headers.put(HEADER_SOURCE, source);
	}
	
	@Override
	public Map<String, Object> getHeaders() {
		return this.headers;
	}

	@Override
	public T getPayload() {
		return this.payload;
	}
}
