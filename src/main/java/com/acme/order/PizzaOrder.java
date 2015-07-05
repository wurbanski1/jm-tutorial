package com.acme.order;

import java.util.Date;

public class PizzaOrder {

	private Date deliveryTime;
	private Date finishTime;
	private OrderStatus status;
	private Customer customer;
	private PizzaType pizzaType;

	public PizzaOrder(OrderStatus status, Customer customer, PizzaType pizzaType) {
		this.status = status;
		this.customer = customer;
		this.pizzaType = pizzaType;
	}

	public void withTime(Date date) {
		this.deliveryTime = date;
	}

	public Date getTime() {

		return deliveryTime;
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
		if (status == OrderStatus.DELIVERED) {
			return deliveryTime.after(finishTime);
		}
		throw new IllegalStateException("The Pizza is not delivered yet!");
	}
	
	public PizzaType getPizzaType() {
		return pizzaType;
	}

}
