package model.discount;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "value", precision = 7, scale = 2)
	private BigDecimal value;

	public Discount(String value) {
		this.value = new BigDecimal(value);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		return basePrice.multiply(BigDecimal.ONE.subtract(value));
	}

	public BigDecimal getCurrentValue() {
		return this.value;
	}

	public long getId() {
		return id;
	}

}
