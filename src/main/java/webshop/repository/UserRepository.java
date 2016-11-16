package webshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	
	public User findByEmail(String email);

}
