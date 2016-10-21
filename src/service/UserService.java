package service;

import model.user.Role;
import model.user.User;
import repository.UserRepository;

public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean register(String email, String password, String name, String address) {
		if(userRepository.findByEmail(email) != null) {
			return false;
		}
		User user = new User(name, email, address, password);
		user.addRole(Role.REGISTERED);
		userRepository.create(user);
		return true;
	}
	
	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return null;
		}
		if(!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	
}
