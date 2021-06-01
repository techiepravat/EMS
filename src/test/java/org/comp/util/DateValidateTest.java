package org.comp.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateValidateTest {

	@Test
	public void formatStringToDate_shouldReturnTrue() throws ParseException {
		String date = "12-11-1991";
		String format = "dd-MM-YYYY";
		Date expectedDate = new SimpleDateFormat(format).parse(date);
		Date actualDate = DateUtil.formatStringToDate(date, format);
		assertEquals(expectedDate, actualDate);
	}

	@Test
	public void formatStringToDate_shouldReturnFalse() throws ParseException {
		String date = "1991-12-11";
		String format = "dd-MM-YYYY";
		Date expectedDate = new SimpleDateFormat(format).parse(date);
		Date actualDate = DateUtil.formatStringToDate(date, format);
		assertEquals(expectedDate, actualDate);
	}

}
