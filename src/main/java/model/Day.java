package model;

public enum Day {
	
	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THURSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(0);
	
	private final int value;
	
	Day(final int newvalue){
		value=newvalue;
	}
	
	public int getValue() { return value; }

}
