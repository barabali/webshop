package webshop.repository;

import java.math.BigDecimal;

public interface UserRepositoryCustom {
	
	public BigDecimal getSpentMoney(long userId);

}
