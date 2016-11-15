package webshop.model;

import java.util.Calendar;

public enum Day {

	MONDAY(1, Calendar.MONDAY), 
	TUESDAY(2, Calendar.TUESDAY), 
	WEDNESDAY(3, Calendar.WEDNESDAY), 
	THURSDAY(4, Calendar.THURSDAY), 
	FRIDAY(5, Calendar.FRIDAY), 
	SATURDAY(6, Calendar.SATURDAY), 
	SUNDAY(0, Calendar.SUNDAY);

	private final int dateValue;
	private int calendarValue;

	Day(final int dateValue, final int calendarValue) {
		this.dateValue = dateValue;
		this.calendarValue = calendarValue;
	}

	public int getValue() {
		return dateValue;
	}

	public static Day fromCalendar(int calendarValue) {
		for (Day day : values()) {
			if (day.calendarValue == calendarValue) {
				return day;
			}
		}

		throw new IllegalArgumentException("Unknown day of week");
	}

	public static Day fromDate(int dateValue) {
		for (Day day : values()) {
			if (day.dateValue == dateValue) {
				return day;
			}
		}

		throw new IllegalArgumentException("Unknown day of week");
	}

}
