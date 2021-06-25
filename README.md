<p align="center">
	<a href="" rel="noopener">
	 <img width=190px height=160px src="adeo.jpg" alt="Project logo">
 </a>
</p>

<h3 align="center">Pro Replenishment coding challenge</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]()

</div>

---

Good reading! 🌈

tags : `Multithreading` `Stream` `Project Reactor`

## 📝 Table of Contents
- [Presentation](#presentation)
- [Coding challenge](#coding-challenge)
	- [Multithreading and concurrent processing](#multithreading-and-concurrent-processing)
		- [Express yourself broker](#express-yourself-broker)
	- [Stream](#stream)
		- [Products sale](#products-sale)
	- [Project Reactor](#project-reactor)
		- [Combining Publishers](#combining-publishers)

## Presentation

Welcome 😀 

This file presents several trainings on the Java language. Indeed, you guessed it, it is like a `coding challenge`. For the moment, one `coding challenge` is available on `Stream` With Java.

## Coding challenge

### Multithreading and concurrent processing

### Express yourself broker

I propose you an another coding challenge using the Java threads.  
Here, you have the opportunity to express yourself ! 😀
Indeed, you will have to implement the `ExpressYourselfBroker` component. The basic idea is to send several messages to `ExpressYourselfBroker` component. With the help of the `Queue` and `Thread` components, you will consume these messages and display them in the Java console.

---

### Stream

### Products sale

I propose you a first coding challenge using the `Stream` with the Java language.

You are several components like the `WebClient`, `BusinessUnit`,  `ProductName` and `ProductSaleEnrichment`.

These components allows you to retrieve many data of `ProductSale`. The `WebClient` has a single method named `getProductSale` and returns `Stream<ProductSale>`. 
This component simulates an http request to REST API.

Now, your job is to add several features to filter, sort and aggregate the data.

- Sorted by product quantity for the FR and IT BUs
- Sorted by the total amount for all BUs
- Get the product with the lowest sale for all BUs
- Get the product with the highest total amount for all BUs
- Get the product with the highest total amount with an aggregation by the product name

---

### Project Reactor

### Combining Publishers

I propose you a coding challenge about the combining `Publishers ` with Project Reactor.

You have the `WebClient` component which allows you to retrieve the products flow and products sale flow. This component simulates an http request to REST API.

Now, your job is to realize an aggregation flow.

- Step 1 : get the products flow
- Step 2 : get the products sale flow
- Step 3 : realize an aggregation flow





