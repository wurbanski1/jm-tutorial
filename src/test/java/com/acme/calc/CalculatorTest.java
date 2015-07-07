package com.acme.calc;

import org.junit.Assert;
<<<<<<< HEAD
import org.junit.Rule;
=======
>>>>>>> upstream/master
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
<<<<<<< HEAD
		Assert.assertTrue(result == 11);

=======
		Assert.assertFalse(result.isNaN());
		Assert.assertTrue(result == 11);
>>>>>>> upstream/master
	}

	private double firstNumber;
	private double secondNumber;
	private Exception thrown;
	@Test
<<<<<<< HEAD
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
	
	@Test (expected = 	DivisorCannotBeZeroException.class)
	public void divisionShouldReturnDivisorException() {
		//given
		double firstNumber = 10.0;
		double secondNumber = 0.0;
		//when
		Double result = calculator.divide(firstNumber, secondNumber);
		
	}
=======
	public void divisionShouldThrowExceptionWhenDivisorIsZero() {
		givenNumbersWithZero();
		catchException(() -> calculator.divide(firstNumber,secondNumber));
		assertException(DivisorCannotBeZeroException.class);
	}

	private void catchException(Runnable runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			thrown = e;
		}
	}

	private void assertException(Class<DivisorCannotBeZeroException> expectedExceptionClass) {
		Assert.assertNotNull(thrown);
		Assert.assertTrue(expectedExceptionClass.equals(thrown.getClass()));
	}
	private void assertThat(Exception e, Class<?> expectedClass) {
		Assert.assertTrue(e.getClass().equals(expectedClass));
	}
	private void thenCorrectExceptionIsThrons(Exception e) {
		Assert.assertTrue(e.getClass().equals(
				DivisorCannotBeZeroException.class));
	}
>>>>>>> upstream/master
	// TODO division and multiplication test!
	private void givenNumbersWithZero() {
		firstNumber = 5.0;
		secondNumber = 0.0;
	}

}
