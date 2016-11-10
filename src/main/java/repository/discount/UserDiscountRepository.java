package repository.discount;

import java.math.BigDecimal;

import model.discount.UserDiscount;
import repository.Repository;

public interface UserDiscountRepository extends Repository<UserDiscount> {
	
	public UserDiscount findByLimit(BigDecimal limit);

}
