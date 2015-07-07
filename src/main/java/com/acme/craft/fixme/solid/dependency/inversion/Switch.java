package com.acme.craft.fixme.solid.dependency.inversion;

public class Switch {

	private boolean pressed;
	private Switchable device;
	
	public Switch(Switchable device) {
		this.device = device;
	}

	private void pressSwitch() {
		pressed = !pressed;
		if (pressed) {
			device.setOn();
		} else {
			device.setOff();
		}
	}
}
