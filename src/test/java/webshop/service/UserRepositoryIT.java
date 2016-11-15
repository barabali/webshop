package webshop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import webshop.model.user.User;
import webshop.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIT {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testFindByEmail(){
		
		String email = "user1@gmail.com";
		userRepository.save(new User("user1", email, "address", "pass"));
		User userFound = userRepository.findByEmail(email);
		assertNotNull(userFound);
	}

}
