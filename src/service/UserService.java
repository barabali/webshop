package service;

import java.util.List;

import model.order.Order;
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
	
	public boolean admin_changePassword(String email,String newpassword){
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return false;
		}
		user.setPassword(newpassword);
		userRepository.update(user);
		return true;
	}

	public List<Order> getPreviousOrders(long user_id){
		return userRepository.getPreviousOrders(user_id);		
	}
	
	public boolean changeUserData(String email, String password, String name, String address){
		User user = userRepository.findByEmail(email);
		user.setAddress(address);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		userRepository.update(user);
		return true;
	}
	
}
