package model;

import java.util.Map;

import model.user.User;

public class Order {

	private Long id;
	private User user;
	private Map<Product, Integer> products;

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
