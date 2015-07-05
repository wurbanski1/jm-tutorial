package com.acme.order;

import java.util.Date;

public interface DeliveryTimeService {

	Date getTime(Customer customer, PizzaType type);

}
