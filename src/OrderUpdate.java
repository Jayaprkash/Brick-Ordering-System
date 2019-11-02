package com.brick.store.orders;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderUpdate {

	private int noOfBricksWanted;

	@JsonCreator
	public OrderUpdate(@JsonProperty("noOfBricksWanted") int noOfBricksWanted) {
		this.noOfBricksWanted = noOfBricksWanted;
	}

	public int getNoOfBricksWanted() {
		return noOfBricksWanted;
	}
	
}
