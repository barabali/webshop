package model;

import java.math.BigDecimal;
import java.util.Date;

public class DailyDiscount extends Discount{
	private Day day;
	
	public DailyDiscount(String value,Day day) {
		super(value);
		this.day=day;
	}
	
	@Override
	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		Date now = new Date();
		if(day.getValue()==now.getDay()) {
			return super.calculateDiscount(basePrice);
		}
		return basePrice;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

}
