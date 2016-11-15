package webshop.model.discount;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import webshop.model.Day;

@Entity
@Table(name = "daily_discount")
public class DailyDiscount extends Discount {

	@Enumerated(EnumType.STRING)
	private Day day;

	public DailyDiscount(String value, Day day) {
		super(value);
		this.day = day;
	}

	@Override
	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		Date now = new Date();
		if (day.getValue() == now.getDay()) {
			return super.calculateDiscount(basePrice);
		}
		return basePrice;
	}

	@Override
	public BigDecimal getCurrentValue() {
		Date now = new Date();
		if (day.getValue() == now.getDay()) {
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
