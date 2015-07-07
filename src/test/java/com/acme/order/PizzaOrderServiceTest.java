package com.acme.order;

<<<<<<< HEAD
import java.sql.Date;

=======
>>>>>>> upstream/master
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
<<<<<<< HEAD

=======
>>>>>>> upstream/master
		pizzaOrderService = new PizzaOrderService(mailSender, orderDatabase,
				orderFactory, deliveryTimeService, messageTemplate);
	}

	@Test
<<<<<<< HEAD
	public void cancelledOrderShouldBePersistedAndEmailShouldBeSent() {
=======
	public void cancelledOrderShouldBePersistedAndEmailShoudlBeSent() {
>>>>>>> upstream/master
		// given
		String pizzaOrderId = "fake_id";
		PizzaOrder givenPizzaOrder = givenPizzaOrder();
		// stub
		OrderCanceledTemplate template = Mockito
				.mock(OrderCanceledTemplate.class);
		Mockito.when(orderDatabase.get(Mockito.anyString())).thenReturn(
				givenPizzaOrder);
		Mockito.when(messageTemplate.getCancelTemplate()).thenReturn(template);
		// when
		pizzaOrderService.cancelOrder(pizzaOrderId);
		// then
		Assert.assertTrue(givenPizzaOrder.isCancelled());
<<<<<<< HEAD

=======
>>>>>>> upstream/master
		ArgumentCaptor<String> sentEmailAddress = ArgumentCaptor
				.forClass(String.class);
		Mockito.verify(mailSender).send(Mockito.any(Template.class),
				sentEmailAddress.capture());
<<<<<<< HEAD
		// Assert.assertTrue(sentEmailAddress.getValue().equals(givenPizzaOrder.getEmail()));
=======
		Assert.assertTrue(sentEmailAddress.getValue().equals(
				givenPizzaOrder.getEmail()));
>>>>>>> upstream/master

		ArgumentCaptor<PizzaOrder> savedPizzaOrder = ArgumentCaptor
				.forClass(PizzaOrder.class);
		Mockito.verify(orderDatabase).save(savedPizzaOrder.capture());
<<<<<<< HEAD
		Assert.assertTrue(savedPizzaOrder.getValue().equals(givenPizzaOrder));

	}
<<<<<<< HEAD
	
	@Test
	public void createdOrderShouldBeEstimatedAndEmailShouldBeSent() {
		// given
		Customer customer = givenCustomer();
		PizzaOrder pizzaOrder = Mockito.mock(PizzaOrder.class);
=======

	@Test
	public void createdOrderShouldBeEstimatedAndEmailShouldBeSent()
			throws Exception {
		// given
		Customer customer = givenCustomer();
		PizzaOrder givenPizzaOrder = givenPizzaOrder();
>>>>>>> f963400c3806ece8b4bbb380a7d8b06e48417500
		// stub
		Date date = Mockito.mock(Date.class);
		Mockito.when(
				orderFactory.create(Mockito.anyObject(), Mockito.anyObject()))
				.thenReturn(givenPizzaOrder);
		Mockito.when(
				deliveryTimeService.getTime(Mockito.anyObject(),
						Mockito.anyObject())).thenReturn(date);
		// when
		pizzaOrderService.createOrder(customer, Mockito.anyObject());
		// then
		ArgumentCaptor<PizzaOrder> savedPizzaOrder = ArgumentCaptor
				.forClass(PizzaOrder.class);
		try {
			orderDatabase.save(givenPizzaOrder);
		} catch (Exception ex) {
			Assert.assertEquals("My exception message.", ex.getMessage());
		}
		Mockito.verify(orderDatabase).save(savedPizzaOrder.capture());

		ArgumentCaptor<String> sentEmailAddress = ArgumentCaptor
				.forClass(String.class);
		// Mockito.verify(mailSender).send(Mockito.any(Template.class),
		// sentEmailAddress.capture());
		// Assert.assertTrue(sentEmailAddress.getValue().equals(givenPizzaOrder.getEmail()));
	}

	private PizzaOrder givenPizzaOrder() {
		Customer customer = givenCustomer();
		PizzaType pizzaType = Mockito.mock(PizzaType.class);
		PizzaOrder givenOrder = new PizzaOrder(customer, pizzaType);
=======
		
		Assert.assertTrue(savedPizzaOrder.getValue().equals(givenPizzaOrder));

	}

	private PizzaOrder givenPizzaOrder() {
		Customer customer = givenCustomer();
		PizzaType type = Mockito.mock(PizzaType.class);
		PizzaOrder givenOrder = new PizzaOrder(customer, type);
>>>>>>> upstream/master
		return givenOrder;
	}

	private Customer givenCustomer() {
<<<<<<< HEAD
		Customer customer = new Customer();
		return customer;
	}

=======
		// String customerEmail = "fake_email";
		Customer customer = new Customer();
		return customer;
	}
>>>>>>> upstream/master
}
