package model;

import java.math.BigDecimal;

public class DailyDiscount {
	
	private Day day;
	
	private BigDecimal discount;
	
	public void setDailyDiscount(BigDecimal d){
		this.discount=d;
	}

	public BigDecimal getDailyDiscount() {
		return discount;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

}
