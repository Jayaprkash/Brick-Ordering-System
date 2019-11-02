package com.brick.store.orders;

import java.util.UUID;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/* Displays the Status of an Order */
public class Order implements Identifiable<String> {

	public enum State {
		OPEN, DISPATCHED;
	}

	private int noOfBricksWanted;

	@JsonIgnore
	private State state;

	@JsonInclude(Include.NON_NULL)
	private String reference;

	@JsonCreator
	public Order(@JsonProperty("noOfBricksWanted") int noOfBricksWanted) {
		this.noOfBricksWanted = noOfBricksWanted;
		this.state = State.OPEN;
		this.reference = UUID.randomUUID().toString();
	}

	public String getReference() {
		return reference;
	}

	public int getNoOfBricksWanted() {
		return noOfBricksWanted;
	}

	public State getState() {
		return state;
	}

	@Override
	public String getId() {
		return getReference();
	}

	public static Order from(NewOrder newOrder) {
		return new Order(newOrder.getNoOfBricksWanted());
	}

	public void update(OrderUpdate orderUpdate) throws OrderCannotBeUpdatedException {
		if(state == state.DISPATCHED) {
			throw new OrderCannotBeUpdatedException(reference);
		}
		noOfBricksWanted = orderUpdate.getNoOfBricksWanted();
	}

	public void fulfil(FulfilOrder fulfilOrder) {
		state = State.DISPATCHED;
	}

	public boolean isFulfilable() {
		return state == State.OPEN;
	}

}
