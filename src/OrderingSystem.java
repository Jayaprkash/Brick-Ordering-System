package com.brick.store.orders;

import java.util.List;

public interface OrderingSystem {

	/* Create a New Order */
	Order createOrder(NewOrder newOrder);

	/* Get an Order that was created */
	Order getOrder(String reference) throws OrderNotFoundException;

	/* Get all the Orders that have been created */
	List<Order> getAllOrders();

	/* Clear all the orders */
	void clear();

	/* Update an existing order*/
	Order updateOrder(String reference, OrderUpdate orderUpdate) throws OrderUpdatedException;
	
	/* Fulfilling the particular order is dispatched or not	 */
	Order fulfilOrder(String reference, FulfilOrder fulfilOrder) throws OrderException;

}
