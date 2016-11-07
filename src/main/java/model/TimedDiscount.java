package model;

import java.math.BigDecimal;
import java.util.Date;

public class TimedDiscount extends Discount {

	public TimedDiscount(String value, Date startsAt, Date expiresAt) {
		super(value);
		this.startsAt = startsAt;
		this.expiresAt = expiresAt;
	}

	private Date startsAt;
	private Date expiresAt;

	@Override
	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		Date now = new Date();
		if(now.after(expiresAt) || now.before(startsAt)) {
			return basePrice;
		}
		return super.calculateDiscount(basePrice);
	}

	@Override
	public BigDecimal isAvalibe(){
		Date now = new Date();
		if(now.after(expiresAt) || now.before(startsAt)) {
			return BigDecimal.valueOf(0.0);
		}
		return super.isAvalibe();
	}
	public Date getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}
	
	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
}
