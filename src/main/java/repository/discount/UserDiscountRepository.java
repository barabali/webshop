package repository;

import java.math.BigDecimal;

import model.UserDiscount;

public interface UserDiscountRepository extends Repository<UserDiscount> {
	
	public UserDiscount findByLimit(BigDecimal limit);

}
