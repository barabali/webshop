package service;

import java.util.List;

import model.order.Order;
import model.order.OrderStatus;
import model.user.Role;
import model.user.User;
import repository.UserRepository;

public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void register(String email, String password, String name, String address) {
		if(userRepository.findByEmail(email) != null) {
			return;
		}
		User user = new User(name, email, address, password);
		user.addRole(Role.REGISTERED);
		userRepository.create(user);
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
	
	public void changePassword(String email, String newpassword){
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return;
		}
		user.setPassword(newpassword);
		userRepository.update(user);
	}

	//TODO: unnecessary
	public List<Order> getPreviousOrders(long userId){
		return userRepository.getPreviousOrders(userId);		
	}
	
	//TODO: unnecessary
	public void changeUserData(String email, String password, String name, String address){
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return;
		}
		user.setAddress(address);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		userRepository.update(user);
	}
	
	public void setOrderStatus(String email, long orderId,OrderStatus status){
		User user = userRepository.findByEmail(email);
		Order order = user.findById(orderId);
		order.setOrder(status);
		userRepository.update(user);
	}
	
}
