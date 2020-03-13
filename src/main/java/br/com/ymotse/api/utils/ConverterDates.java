package br.com.ymotse.api.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
public class ConverterDates {

	private static DecimalFormat twoDecimals = new DecimalFormat("00");

	private static int normalizeMonthIndex(int month) {
		return month + 1;
	}

	/**
	 * @param Calendar
	 * @return String(YYYY-MM-DD HH24:MI:SS)
	 */
	public String convertCalendarToStringDateAndTime(Calendar calendar) {

		if (calendar == null)
			return null;

		int year = calendar.get(Calendar.YEAR);
		String month = twoDecimals.format(normalizeMonthIndex(calendar.get(Calendar.MONTH)));
		String dayOfMonth = twoDecimals.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

		String hourOfDay = twoDecimals.format(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = twoDecimals.format(calendar.get(Calendar.MINUTE));
		String second = twoDecimals.format(calendar.get(Calendar.SECOND));

		String date = year + "-" + month + "-" + dayOfMonth;
		String time = hourOfDay + ":" + minute + ":" + second;

		return date + " " + time;
	}

}
