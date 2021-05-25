package org.comp.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateValidateTest {

	@Test
	public void test() throws ParseException {
		String date = "12-11-1991";
		String format = "dd-MM-YYYY";
		Date actual = DateUtil.formatStringToDate(date, format);
		assertEquals(date, actual);
	}

}
