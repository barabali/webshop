package model;

import java.math.BigDecimal;

public enum DailyDiscount {
	
	Monday,Thuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
	
	private BigDecimal discount;
	
	public void setDailyDiscount(BigDecimal d){
		this.discount=d;
	}

	public BigDecimal getDailyDiscount() {
		return discount;
	}

}
