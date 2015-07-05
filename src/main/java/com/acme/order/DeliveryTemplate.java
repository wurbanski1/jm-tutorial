package com.acme.order;

import java.util.Date;

public interface DeliveryTemplate extends Template {

	void putTime(Date date);

	void putAddress(Object address);

}
