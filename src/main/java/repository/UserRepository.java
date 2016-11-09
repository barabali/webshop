package repository;


import java.math.BigDecimal;

import model.user.User;

public interface UserRepository extends Repository<User> {
	
	public User findByEmail(String email);
	
	public BigDecimal getSpentMoney(long user_id);
	
}
