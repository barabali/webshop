package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.RegistrationFailedException;
import model.user.User;
import repository.UserRepository;

public class UserServiceTest {
	
	static UserRepository userRepository;
	static UserService userService;
	
	@BeforeClass
    public static void setUp() {
		userRepository = new DummyUserRepository();
		userService = new UserService(userRepository);
    }
	
	@Test
	public void testRegisterNewUser() throws RegistrationFailedException {
		String email = "abcdef@test.com";
		String password = "password";
		String name = "Test Name";
		String address = "1111 Test, Address 1.";
		userService.register(email, password, name, address);
	}
	
	@Test(expected = RegistrationFailedException.class)
	public void testRegisterNewUserNullParameters() throws RegistrationFailedException {
		String email = "abcdef@test.com";
		String password = null;
		String name = "Test Name";
		String address = "1111 Test, Address 1.";
		userService.register(email, password, name, address);
	}
	
	@Test(expected = RegistrationFailedException.class)
	public void testRegisterExistingUser() throws RegistrationFailedException {
		String email = "alreadyExists@test.com";
		String password = "password";
		String name = "Test Name";
		String address = "1111 Test, Address 1.";
		userService.register(email, password, name, address);
	}
	
	@Test
	public void testLogin() {
		User user = userService.login("alreadyExists@test.com", "password");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testLoginNonRegisteredUser() {
		User user = userService.login("nonRegistered@test.com", "password");
		Assert.assertNull(user);
	}
	
	@Test
	public void testLoginWrongPassword() {
		User user = userService.login("alreadyExists@test.com", "wrongPassword");
		Assert.assertNull(user);
	}


}
