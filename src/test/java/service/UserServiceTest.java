package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import exception.RegistrationFailedException;
import model.user.User;
import repository.OrderRepository;
import repository.UserRepository;

public class UserServiceTest {
	
	static UserRepository userRepository;
	static OrderRepository orderRepository;
	static UserService userService;
	static User user;
	
	@BeforeClass
    public static void setUp() {
		userRepository = mock(UserRepository.class);
		orderRepository = mock(OrderRepository.class);
		userService = new UserService(userRepository, orderRepository);
		user=new User("Test Name","abcdef@test.com","1111 Test, Address 1.","password");
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
		when(userService.userRepository.findByEmail("alreadyExists@test.com")).thenReturn(user);
		String email = "alreadyExists@test.com";
		String password = "password";
		String name = "Test Name";
		String address = "1111 Test, Address 1.";
		userService.register(email, password, name, address);
	}
	
	@Test
	public void testLogin() {
		when(userService.userRepository.findByEmail("alreadyExists@test.com")).thenReturn(user);
		User user = userService.login("alreadyExists@test.com", "password");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testLoginNonRegisteredUser() {
		when(userService.userRepository.findByEmail("nonRegistered@test.com")).thenReturn(null);
		User user = userService.login("nonRegistered@test.com", "password");
		Assert.assertNull(user);
	}
	
	@Test
	public void testLoginWrongPassword() {
		when(userService.userRepository.findByEmail("alreadyExists@test.com")).thenReturn(user);
		User user = userService.login("alreadyExists@test.com", "wrongPassword");
		Assert.assertNull(user);
	}


}
