package org.comp.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateMobileNoTest {

	public void setUp() {

	}

	@Test
	public void validateMobileNo_shouldReturnTrue() {
		long mobileNo = 9876756545l;
		boolean actual = ValidateUtil.validateMobileNumber(mobileNo);
		assertEquals(true, actual);
	}

	@Test
	public void validateMobileNo_shouldReturnFalse() {
		long mobileNo = 1234l;
		boolean actual = ValidateUtil.validateMobileNumber(mobileNo);
		assertEquals(false, actual);
	}

}
