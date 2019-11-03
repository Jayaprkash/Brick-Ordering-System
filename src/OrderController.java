package com.brick.store.rest;

import java.util.List;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brick.store.orders.FulfilOrder;
import com.brick.store.orders.NewOrder;
import com.brick.store.orders.Order;
import com.brick.store.orders.OrderException;
import com.brick.store.orders.OrderUpdatedException;
import com.brick.store.orders.OrderNotFoundException;
import com.brick.store.orders.OrderUpdate;
import com.brick.store.orders.OrderingSystem;

@RestController
@ExposesResourceFor(Order.class)
@RequestMapping(path = "/orders")
class OrderController {

	private final OrderResourceAssembler assembler;
	private final OrderingSystem orderingSystem;

	public OrderController(OrderingSystem orderingSystem, OrderResourceAssembler assembler) {
		this.orderingSystem = orderingSystem;
		this.assembler = assembler;
	}

	@PostMapping
	public OrderResource postNewOrder(@RequestBody NewOrder newOrder) {
		return assembler.toResource(orderingSystem.createOrder(newOrder));
	}

	@GetMapping
	public List<OrderResource> get() {
		return assembler.toResources(orderingSystem.getAllOrders());
	}

	@PostMapping(path = "/{reference}", consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public OrderResource postOrderCommand(@PathVariable("reference") String reference,
			@RequestBody(required=false) OrderUpdate orderUpdate) throws OrderNotFoundException, OrderException, OrderUpdatedException {
		if (orderUpdate != null) {
			return assembler.toResource(orderingSystem.updateOrder(reference, orderUpdate));
		} else {
			
			return assembler.toResource(orderingSystem.fulfilOrder(reference, new FulfilOrder()));
		}
	}

	@GetMapping(path = "/{reference}")
	public OrderResource getOrder(@PathVariable("reference") String reference) throws OrderNotFoundException {
		return assembler.toResource(orderingSystem.getOrder(reference));
	}

	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleOrderNotFoundException() {

	}
	
	@ExceptionHandler(OrderException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleOrderException() {

	}
	
	@ExceptionHandler(OrderUpdatedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleOrderUpdatedException() {

	}

}
