package com.acme.craft.fixme.solid.liskov.substitution;

public class Square extends Rectangle {

	@Override
	public void setWidth(int width) {
		this.width = width;
		this.height = width;
	}

	@Override
	public void setHeight(int height) {
		this.width = height;
		this.height = height;
	}
}
