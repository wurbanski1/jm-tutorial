package com.acme.order;

import java.util.Date;

public class PizzaOrder {

	private Date estimatedDeliveryTime;
	private Date finishTime;
	private OrderStatus status;
	private Customer customer;
	private PizzaType pizzaType;

	public PizzaOrder(OrderStatus status, Customer customer, PizzaType pizzaType) {
		this.status = status;
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
		this.status = OrderStatus.CANCELLED;
	}

	public String getEmail() {

		return customer.getEmail();
	}

	public String getAddress() {
		return customer.getAddress();
	}

	public void deliver() {
		this.status = OrderStatus.DELIVERED;
		finishTime = new Date();
	}

	public boolean wasDeliveredOnTime() {
		if (estimatedDeliveryTime == null) {
			return true;
		}
		if (status == OrderStatus.DELIVERED) {
			return estimatedDeliveryTime.after(finishTime);
		}
		throw new IllegalStateException("The Pizza is not delivered yet!");
	}

	public PizzaType getPizzaType() {
		return pizzaType;
	}

}
