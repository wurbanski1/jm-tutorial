package com.acme.craft.fixme.solid.single.responsibility.modem;

interface Modem extends Transmitter {
	void dial(String pno);

	void hangup();

}
