package com.brick.store.rest;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.brick.store.orders.Order;

@Component
class OrderResourceAssembler extends ResourceAssemblerSupport<Order, OrderResource> {

	private EntityLinks entityLinks;

	public OrderResourceAssembler(EntityLinks entityLinks) {
		super(OrderController.class, OrderResource.class);
		this.entityLinks = entityLinks;
	}

	@Override
	public OrderResource toResource(Order order) {
		OrderResource resource = createResourceWithId(order.getReference(), order);
		resource.noOfBricksWanted = order.getNoOfBricksWanted();
		resource.reference = order.getReference();
		resource.state = order.getState().toString();
		resource.add(entityLinks.linkToCollectionResource(Order.class).withRel("orders"));
		resource.add(entityLinks.linkToCollectionResource(Order.class).withRel("create-order"));
		if(order.isFulfilable()) {
			resource.add(entityLinks.linkToSingleResource(order).withRel("fulfil-order"));
		}
		return resource;
	}

}
