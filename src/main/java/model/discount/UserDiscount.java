package model.discount;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_discount")
public class UserDiscount extends Discount {

	@Column(name = "lower_limit", precision = 7, scale = 2)
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
