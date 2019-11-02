package com.brick.store.orders;

@SuppressWarnings("serial")
public class OrderUpdatedException extends Exception {

	public OrderUpdatedException(String reference) {
		super("Order with reference " + reference + " could not be updated");
	}

}
