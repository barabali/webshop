package repository;

import java.util.List;
import model.order.Order;

public interface OrderRepository extends Repository<Order>{

	public List<Order> getPreviousOrders(long user_id);

}
