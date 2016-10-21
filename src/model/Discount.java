package model;

import java.math.BigDecimal;

public class Discount {
	
	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		return basePrice.multiply(BigDecimal.ONE.subtract(value));
	}

}
