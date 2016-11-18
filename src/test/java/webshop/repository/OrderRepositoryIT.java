package webshop.repository;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import webshop.model.CombinedOrderItem;
import webshop.model.OrderItem;
import webshop.model.order.Order;
import webshop.model.user.User;
import webshop.repository.OrderRepository;
import webshop.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryIT {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testFindByUser(){
		String email = "user1@gmail.com";
		User user1= new User("user1", email, "address", "pass");
		userRepository.save(user1);
		User userFound= userRepository.findByEmail(email);
		orderRepository.save(new Order(user1,new HashMap<OrderItem,Integer>(),new HashMap<CombinedOrderItem,Integer>()));
		List<Order> ordersFound= orderRepository.findByUserId(userFound.getId());
		assertNotNull(ordersFound);
	}

}
