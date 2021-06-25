package com.adeo.pro.replenishment.coding.challenge.thread.expressyourself;

import java.util.concurrent.ExecutorService;

public interface PoolStopHandler {

	/**
	 * @param pool
	 */
	public void shutdownAndTerminate(ExecutorService pool);
}
