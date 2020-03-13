package br.com.ymotse.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yitshhaq.fukushima
 *
 */
@SpringBootTest
public class ConverterDatesTests {

	private ConverterDates converterDates;

	private Calendar calendar;

	private String stringCalendarExpected;

	@Before
	public void setup() {
		converterDates = new ConverterDates();

		calendar = Calendar.getInstance();
		calendar.set(2020, 01, 10, 15, 00, 00);

		stringCalendarExpected = "2020-02-10 15:00:00";
	}

	@Test
	public void convertCalendarToStringDateAndTime_test() {

		assertEquals(stringCalendarExpected, converterDates.convertCalendarToStringDateAndTime(calendar));

		assertEquals(null, converterDates.convertCalendarToStringDateAndTime(null));
	}

}
