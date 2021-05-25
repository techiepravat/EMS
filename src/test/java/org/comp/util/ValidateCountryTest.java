package org.comp.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateCountryTest {

	public void setUp() {

	}

	@Test
	public void validateCountry_shouldReturnTrue() {
		String[] countries = { "USA", "UK", "UAE", "INDIA" };
		String country = "UAE";
		boolean actual = ValidateUtil.validateCountry(countries, country);
		assertEquals(true, actual);
	}

	@Test
	public void validateCountry_shouldReturnFalse() {
		String[] countries = { "USA", "UK", "UAE", "INDIA" };
		String country = "srilanka";
		boolean actual = ValidateUtil.validateCountry(countries, country);
		assertEquals(false, actual);
	}
}
