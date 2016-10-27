package model;

import java.math.BigDecimal;

//TODO: A suggestion to make our life easier with JPA later:
//An enum Day would be introduced. And DailyDiscount would be a class with two members:
//a Day day and BigDecimal discount. 
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
