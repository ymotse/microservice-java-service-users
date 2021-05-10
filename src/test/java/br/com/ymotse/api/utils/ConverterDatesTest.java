package br.com.ymotse.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author yitshhaq.fukushima
 *
 */
@ExtendWith(SpringExtension.class)
public class ConverterDatesTest {

	private ConverterDates converterDates;

	private Calendar calendar;

	private String stringCalendarExpected = "2020-02-10 15:00:00";

	
	@Test
	void convertCalendarToStringDateAndTime_test() {

		converterDates = new ConverterDates();

		calendar = Calendar.getInstance();
		calendar.set(2020, 01, 10, 15, 00, 00);

		assertEquals(stringCalendarExpected, converterDates.convertCalendarToStringDateAndTime(calendar));

		assertEquals(null, converterDates.convertCalendarToStringDateAndTime(null));
	}

}
