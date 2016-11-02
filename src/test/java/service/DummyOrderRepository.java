package service;

import java.util.ArrayList;
import java.util.List;

import model.order.Order;
import repository.OrderRepository;

public class DummyOrderRepository implements OrderRepository {

	private List<Order> orders = new ArrayList<Order>();

	@Override
	public void create(Order entity) {
		orders.add(entity);

	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public void update(Order entity) {

	}

	@Override
	public Order findById(long id) {
		return null;
	}

	@Override
	public List<Order> findAll() {
		return orders;
	}

	@Override
	public List<Order> getPreviousOrders(long user_id) {
		return null;
	}

}
