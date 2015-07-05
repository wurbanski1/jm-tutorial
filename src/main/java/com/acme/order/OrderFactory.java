package com.acme.order;

public interface OrderFactory {

	PizzaOrder create(Customer customer, PizzaType type);

}
