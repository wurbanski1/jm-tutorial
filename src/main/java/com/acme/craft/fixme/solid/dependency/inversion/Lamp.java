package com.acme.craft.fixme.solid.dependency.inversion;

import lombok.Data;

@Data
public class Lamp implements Switchable {

	private boolean on = false;

	@Override
	public void setOn() {
		// ...
		
	}

	@Override
	public void setOff() {
		// ...
	}

	
}
