package com.acme.order;

public interface OrderDatabase {

	void save(PizzaOrder order);

	void rollback();

	PizzaOrder get(String pizzaOrderId);

}
