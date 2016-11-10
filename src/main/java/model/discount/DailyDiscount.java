package model;

import java.math.BigDecimal;
import java.util.Date;

import javax.jws.soap.SOAPBinding;

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
	public BigDecimal isAvalibe(){
		Date now = new Date();
		if(day.getValue()==now.getDay()) {
			return super.isAvalibe();
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
