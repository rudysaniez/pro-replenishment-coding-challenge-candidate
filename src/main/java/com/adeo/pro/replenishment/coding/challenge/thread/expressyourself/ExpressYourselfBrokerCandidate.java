package com.adeo.pro.replenishment.coding.challenge.thread.expressyourself;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpressYourselfBrokerCandidate implements BrokerConsumer<Message<?>>, MessageChannel, BrokerHandler, PoolStopHandler {

	/**
	 * The main.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		ExpressYourselfBrokerCandidate broker = new ExpressYourselfBrokerCandidate(10);
		
		//A consumer is needed...
		
		//Several messages are sent.
		broker.send(new GenericMessage<String>(" > Hi everybody, how are you ?", "Rudy"));
		broker.send(new GenericMessage<String>(" > My name is Nathan and i am very happy to be here.", "Nathan"));
		broker.send(new GenericMessage<String>(" > My name is Stéphane and i like to play with Lego.", "Stéphane"));
		broker.send(new GenericMessage<String>(" > My name is Christophe, i like to play basket ball!", "Christophe"));
		broker.send(new GenericMessage<String>(" > How are you guys?", "John"));
		
		TimeUnit.SECONDS.sleep(6);
		broker.stop();
	}
	
	/*
	 * Express yourself Broker implementation.
	 */
	
	private final BlockingQueue<Message<?>> queue;
	private final ExecutorService pool;
	
	/**
	 * {@inheritDoc}
	 */
	public ExpressYourselfBrokerCandidate(int queueCapacity) {
		
		//Must be initialized...
		queue = null;
		pool = null;
		start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		log.info(" > The broker is start.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
		//Must be implemented...
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(Message<?> message) {

		log.info(" > A message will be sent : {} at {}.", message.getPayload().toString(), LocalDateTime.now());
		
		if(Objects.isNull(message) || Objects.isNull(message.getPayload()) || 
				StringUtils.isBlank(message.getHeaders().get(GenericMessage.HEADER_SOURCE).toString())) return;
		
		//Must be implemented...
	}
	
	@Override
	public void addConsumer(Consumer<Message<?>> consumer) {
		log.info(" > One consumer is added.");
		pool.execute(new BrokerConsumerRunnable(consumer, queue));
	}
	
	/**
	 * This component consumes the {@link Queue} and invoke the consumer function. 
	 */
	public static class BrokerConsumerRunnable implements Runnable {

		private final Consumer<Message<?>> consumer;
		private final BlockingQueue<Message<?>> queue;
			
		public BrokerConsumerRunnable(Consumer<Message<?>> consumer, BlockingQueue<Message<?>> queue) {
			this.consumer = consumer;
			this.queue = queue;
		}
		
		@Override
		public void run() {
			//Must be implemented...
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void shutdownAndTerminate(ExecutorService pool) {
		//Must be implemented...
	}
}
