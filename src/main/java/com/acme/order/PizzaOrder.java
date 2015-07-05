package com.acme.order;

import java.util.Date;

public class PizzaOrder {

	private Date estimatedDeliveryTime;
	private Date finishTime;
	private OrderStatus state;
	private Customer customer;
	private PizzaType pizzaType;

	public PizzaOrder(OrderStatus status, Customer customer, PizzaType pizzaType) {
		this.state = status;
		this.customer = customer;
		this.pizzaType = pizzaType;
	}

	public void withEstimatedTime(Date date) {
		this.estimatedDeliveryTime = date;
	}

	public Date getEstimatedTime() {
		return estimatedDeliveryTime;
	}

	public void cancel() {
		this.state = OrderStatus.CANCELLED;
	}

	public boolean isCreated() {
		return this.state == OrderStatus.CREATED;
	}

	public boolean isCancelled() {
		return this.state == OrderStatus.CANCELLED;
	}

	public boolean isDelivered() {
		return this.state == OrderStatus.DELIVERED;
	}

	public String getEmail() {
		return customer.getEmail();
	}

	public String getAddress() {
		return customer.getAddress();
	}

	public void deliver() {
		this.state = OrderStatus.DELIVERED;
		finishTime = new Date();
	}

	public boolean wasDeliveredOnTime() {
		if (estimatedDeliveryTime == null) {
			return true;
		}
		if (state == OrderStatus.DELIVERED) {
			return estimatedDeliveryTime.after(finishTime);
		}
		throw new IllegalStateException("The Pizza is not delivered yet!");
	}

	public PizzaType getPizzaType() {
		return pizzaType;
	}

}
