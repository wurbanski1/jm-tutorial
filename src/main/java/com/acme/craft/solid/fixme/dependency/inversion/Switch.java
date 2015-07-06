package com.acme.craft.solid.fixme.dependency.inversion;

public class Switch {

	private Lamp lamp;
	private boolean pressed;

	public Switch(Lamp lamp) {
		this.lamp = lamp;
	}

	private void pressSwitch() {
		pressed = !pressed;
		if (pressed) {
			lamp.setOn(true);
		} else {
			lamp.setOn(false);
		}
	}
}
