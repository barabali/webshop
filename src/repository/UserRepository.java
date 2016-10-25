package repository;

import java.util.List;

import model.order.Order;
import model.user.User;

public interface UserRepository extends Repository<User> {
	
	public User findByEmail(String email);
	
	public long getSpentMoney(long user_id);
	
	public List<Order> getPreviousOrders(long user_id);

}
