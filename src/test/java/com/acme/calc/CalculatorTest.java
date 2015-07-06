package com.acme.calc;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class CalculatorTest {

	private Calculator calculator = new Calculator();

	@Test
	public void additionShouldReturnCorrectResult() {
		// given
		double firstNumber = 5.0;
		double secondNumber = 6.0;
		// when
		Double result = calculator.add(firstNumber, secondNumber);
		// then
		Assert.assertTrue(result == 11);

	}

	@Test
	public void subtractionShouldReturnCorrectResult() {
		// given
		double firstNumber = 6.0;
		double secondNumber = 3.0;
		// when
		Double result = calculator.subtract(firstNumber, secondNumber);
		// then
		Assert.assertTrue(result == 3);
	}
	
	@Test
	public void multiplicationShouldReturnCorrectResult() {
		//given
		double firstNumber = 9.0;
		double secondNumber = 2.0;
		//when
		Double result = calculator.multiply(firstNumber, secondNumber);
		//then
		Assert.assertTrue(result == 18);
	}
	
	@Test
	public void divisionShouldReturnCorrectResult() {
		//given
		double firstNumber = 10.0;
		double secondNumber = 5.0;
		//when
		Double result = calculator.divide(firstNumber, secondNumber);
		//then
		Assert.assertTrue(result == 2);
	}
	
	@Test (expected = DivisorCannotBeZeroException.class)
	public void divisionShouldReturnDivisorException() {
		//given
		double firstNumber = 10.0;
		double secondNumber = 0.0;
		//when
		Double result = calculator.divide(firstNumber, secondNumber);
		
	}
	// TODO division and multiplication test!

}
