package webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webshop.exception.RegistrationFailedException;
import webshop.model.order.Order;
import webshop.model.order.OrderStatus;
import webshop.model.user.Role;
import webshop.model.user.User;
import webshop.repository.OrderRepository;
import webshop.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;

	public UserService(UserRepository userRepository, OrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}

	public void register(String email, String password, String name, String address)
			throws RegistrationFailedException {
		if (email == null || password == null || name == null || address == null
				|| userRepository.findByEmail(email) != null) {
			throw new RegistrationFailedException();
		}
		User user = new User(name, email, address, password);
		user.addRole(Role.REGISTERED);
		userRepository.save(user);
	}

	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return null;
		}
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}

	public void changePassword(String email, String newpassword) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return;
		}
		user.setPassword(newpassword);
		userRepository.save(user);
	}

	public void setOrderStatus(long orderId, OrderStatus status) {
		Order order = orderRepository.findOne(orderId);
		order.setOrder(status);
		orderRepository.save(order);
	}

}
