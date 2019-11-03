package com.brick.store.rest;

import org.springframework.hateoas.ResourceSupport;

class OrderResource extends ResourceSupport {

	int noOfBricksWanted;
	String reference;
	String state;

	public int getNoOfBricksWanted() {
		return noOfBricksWanted;
	}

	public String getReference() {
		return reference;
	}
	
	public String getState() {
		return state;
	}

}
