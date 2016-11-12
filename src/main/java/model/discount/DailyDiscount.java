package model.discount;

import java.math.BigDecimal;
import java.util.Date;
import model.Day;

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
	
	@Override
	public BigDecimal getCurrentValue(){
		Date now = new Date();
		if(day.getValue()==now.getDay()) {
			return super.getCurrentValue();
		}
		return BigDecimal.valueOf(0.0);
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

}
