package com.acme.order;

public interface MailSender {

	void send(Template message, String email);

}
