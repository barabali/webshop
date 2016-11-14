package repository;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	public BigDecimal getSpentMoney(long userId);
	
}
