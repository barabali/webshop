package model.order;

import java.util.Map;

import model.Product;
import model.user.User;

public class Order {

	private Long id;
	private User user;
	//TODO: Product versus OrderItem, we will talk about it
	private Map<Product, Integer> products;
	private OrderStatus order;
	
	//TODO: total price

	public OrderStatus getOrder() {
		return order;
	}

	public void setOrder(OrderStatus order) {
		this.order = order;
	}

	public Order(User user, Map<Product, Integer> products) {
		this.user = user;
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public Long getId() {
		return id;
	}

}
