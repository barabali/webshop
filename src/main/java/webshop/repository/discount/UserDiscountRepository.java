package webshop.repository.discount;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.discount.UserDiscount;

public interface UserDiscountRepository extends JpaRepository<UserDiscount, Long> {
	
	public UserDiscount findByLimit(BigDecimal limit);

}
