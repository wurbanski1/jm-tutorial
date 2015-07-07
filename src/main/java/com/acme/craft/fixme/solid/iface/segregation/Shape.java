package com.acme.craft.fixme.solid.iface.segregation;

import java.awt.*;

public interface Shape {

	int getArea();

	int getWidth();

	int getHeight();

	int getDepth();
	
	int computeDiameter();
	
	int getCornerCount();

	int getDimensions();

	double getVolume();

	double getCircumference();

	Color getColor();

	void setPosition(int x, int y, int z);

}
