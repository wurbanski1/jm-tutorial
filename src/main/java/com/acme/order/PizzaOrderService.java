package com.acme.order;

import java.util.Date;

public class PizzaOrderService {

	private MailSender mailSender;

	private OrderDatabase orderDatabase;

	private OrderFactory orderFactory;

	private DeliveryTimeService deliveryTimeService;
	
	private MessageTemplateService messageTemplate;

	public void createOrder(Customer customer, PizzaType type) {
		try {
			PizzaOrder order = orderFactory.create(customer, type);
			Date date = deliveryTimeService.getTime(customer, type);
			order.withTime(date);
			orderDatabase.save(order);
			mailSender.send(createMessage(order), customer.getEmail());
		} catch (Exception e) {
			orderDatabase.rollback();
		}
	}

	private DeliveryTemplate createMessage(PizzaOrder order) {
		DeliveryTemplate template = messageTemplate.getDeliveryTemplpate();
		template.putTime(order.getTime());
		template.putAddress(order.getAddress());
		return template;
	}

	public void cancelOrder(String pizzaOrderId) {
		PizzaOrder order = orderDatabase.get(pizzaOrderId);
		order.cancel();
		OrderCanceledTemplate template = messageTemplate.getCancelTemplate();
		mailSender.send(template, order.getEmail());
	}

	public void deliverOrder(String pizzaOrderId) {
		PizzaOrder order = orderDatabase.get(pizzaOrderId);
		order.deliver();
	}
}
