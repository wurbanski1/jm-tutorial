package com.flopska.interfacesegregation.bad;

import java.awt.Color;

public interface Shape {

	int getArea();
	int computeDiameter();
	int getWidth();
	int getHeight();
	int getDepth();
	double getVolume();
	double getCircumference();
	int getCornerCount();
	int getDimensions();
	Color getColor();
	void setPosition(int x, int y, int z);
	
}
