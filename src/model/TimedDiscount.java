package model;

import java.math.BigDecimal;
import java.util.Date;

public class TimedDiscount extends Discount {

	private Date expiresAt;

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	@Override
	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		Date now = new Date();
		if(now.after(expiresAt)) {
			return basePrice;
		}
		return super.calculateDiscount(basePrice);
	}

}
