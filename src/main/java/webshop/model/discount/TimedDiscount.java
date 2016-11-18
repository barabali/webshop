package webshop.model.discount;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "timed_discount")
public class TimedDiscount extends Discount {

	@Temporal(TemporalType.DATE)
	@Column(name = "starts_at")
	private Date startsAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "expires_at")
	private Date expiresAt;
	
	public TimedDiscount(){
		super("0");
	}

	public TimedDiscount(String value, Date startsAt, Date expiresAt) {
		super(value);
		this.startsAt = startsAt;
		this.expiresAt = expiresAt;
	}

	@Override
	public BigDecimal calculateDiscount(BigDecimal basePrice) {
		Date now = new Date();
		if (now.after(expiresAt) || now.before(startsAt)) {
			return basePrice;
		}
		return super.calculateDiscount(basePrice);
	}

	@Override
	public BigDecimal getCurrentValue() {
		Date now = new Date();
		if (now.after(expiresAt) || now.before(startsAt)) {
			return BigDecimal.valueOf(0.0);
		}
		return super.getCurrentValue();
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
