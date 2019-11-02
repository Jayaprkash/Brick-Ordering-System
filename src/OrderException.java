package com.brick.store.orders;

@SuppressWarnings("serial")
public class OrderException extends Exception {

	public OrderException(String reference) {
		super("Order with reference " + reference + " could not be fulfilled");
	}

}
