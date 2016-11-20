package webshop.repository;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import webshop.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);

	@Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.user.id=:userId")
	public BigDecimal getSpentMoney(@Param("userId") long userId);
}
