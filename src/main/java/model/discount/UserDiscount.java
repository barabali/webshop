package model.discount;

import java.math.BigDecimal;

public class UserDiscount extends Discount {

	private BigDecimal limit;
	
	public UserDiscount(String limit, String discountValue) {
		super(discountValue);
		this.limit = new BigDecimal(limit);
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}	

}
