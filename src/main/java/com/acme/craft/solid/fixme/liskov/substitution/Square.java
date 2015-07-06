package com.acme.craft.solid.fixme.liskov.substitution;

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
