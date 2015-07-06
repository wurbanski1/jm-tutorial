package com.acme.order;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class PizzaOrderServiceTest {

	private PizzaOrderService pizzaOrderService;
	private MailSender mailSender;
	private OrderDatabase orderDatabase;
	private OrderFactory orderFactory;
	private DeliveryTimeService deliveryTimeService;
	private MessageTemplateService messageTemplate;

	@Before
	public void init() {
		mailSender = Mockito.mock(MailSender.class);
		orderDatabase = Mockito.mock(OrderDatabase.class);
		orderFactory = Mockito.mock(OrderFactory.class);
		deliveryTimeService = Mockito.mock(DeliveryTimeService.class);
		messageTemplate = Mockito.mock(MessageTemplateService.class);

		pizzaOrderService = new PizzaOrderService(mailSender, orderDatabase, orderFactory, deliveryTimeService,
				messageTemplate);
	}

	@Test
	public void cancelledOrderShouldBePersistedAndEmailShouldBeSent() {
		// given
		String pizzaOrderId = "fake_id";
		PizzaOrder givenPizzaOrder = givenPizzaOrder();
		// stub
		OrderCanceledTemplate template = Mockito.mock(OrderCanceledTemplate.class);
		Mockito.when(orderDatabase.get(Mockito.anyString())).thenReturn(givenPizzaOrder);
		Mockito.when(messageTemplate.getCancelTemplate()).thenReturn(template);
		// when
		pizzaOrderService.cancelOrder(pizzaOrderId);
		// then
		Assert.assertTrue(givenPizzaOrder.isCancelled());
		ArgumentCaptor<String> sentEmailAddress = ArgumentCaptor.forClass(String.class);
		Mockito.verify(mailSender).send(Mockito.any(Template.class), sentEmailAddress.capture());
//		Assert.assertTrue(sentEmailAddress.getValue().equals(givenPizzaOrder.getEmail()));

		ArgumentCaptor<PizzaOrder> savedPizzaOrder = ArgumentCaptor.forClass(PizzaOrder.class);
		Mockito.verify(orderDatabase).save(savedPizzaOrder.capture());
		Assert.assertTrue(savedPizzaOrder.getValue().equals(givenPizzaOrder));

	}

	public void createdOrderShouldBeEstimatedAndEmailShouldBeSent() {
		// given
		Customer customer = givenCustomer();
		// stub
		PizzaType pizzaType = Mockito.mock(PizzaType.class);
		OrderFactory orderFactory = Mockito.mock(OrderFactory.class);
		PizzaOrder pizzaOrder = Mockito.mock(PizzaOrder.class);
		Date date = Mockito.mock(Date.class);
		
		Mockito.when(orderFactory.create(Mockito.any(Customer.class), Mockito.any(PizzaType.class)))
				.thenReturn(pizzaOrder);
		Mockito.when(deliveryTimeService.getTime(Mockito.any(Customer.class), Mockito.any(PizzaType.class)))
				.thenReturn(date);
		Mockito.when(pizzaOrder.getEstimatedTime()).thenReturn(date);
		// when
		pizzaOrderService.createOrder(customer, pizzaType);
		// then
		Assert.assertTrue(pizzaOrder.getEstimatedTime().equals(date));

		ArgumentCaptor<PizzaOrder> savedPizzaOrder = ArgumentCaptor.forClass(PizzaOrder.class);
		Mockito.verify(orderDatabase).save(savedPizzaOrder.capture());
		Assert.assertTrue(savedPizzaOrder.getValue().equals(pizzaOrder));
		
		ArgumentCaptor<String> sentEmailAddress = ArgumentCaptor.forClass(String.class);
		Mockito.verify(mailSender).send(Mockito.any(Template.class), sentEmailAddress.capture());
		Assert.assertTrue(sentEmailAddress.getValue().equals(pizzaOrder.getEmail()));

	}

	private PizzaOrder givenPizzaOrder() {
		Customer customer = givenCustomer();
		PizzaType pizzaType = Mockito.mock(PizzaType.class);
		PizzaOrder givenOrder = new PizzaOrder(customer, pizzaType);
		return givenOrder;
	}

	private Customer givenCustomer() {
		Customer customer = new Customer();
		return customer;
	}

}
