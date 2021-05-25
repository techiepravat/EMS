package org.comp.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateMailTest {

	public void setUp() {

	}

	@Test
	public void validateMail_shouldReturnTrue() {
		String emailId = "xyz@gmail.com";
		boolean actual = ValidateUtil.validateMail(emailId);
		assertEquals(true, actual);
	}

	@Test
	public void validateMail_shouldReturnFalse() {
		String emailId = "xyz.com";
		boolean actual = ValidateUtil.validateMail(emailId);
		assertEquals(false, actual);
	}
}
