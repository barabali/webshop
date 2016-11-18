package webshop.repository;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import webshop.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>/*, UserRepositoryCustom*/ {
	
	public User findByEmail(String email);

	//TODO: define query here
	@Query("SELECT u FROM User u WHERE u.id=:userId")
	public BigDecimal getSpentMoney(@Param("userId") long userId);
}
