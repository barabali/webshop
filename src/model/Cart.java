package model;

import java.util.Map;

import model.user.User;
import order.Order;

public class Cart {

	private Long id;
	private User user;
	
	private Map<Product, Integer> products;

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
	
	public Order toOrder() {
		return new Order(user, products);
	}
	
	public void setProductAmount(Product product, int amount) {
		if(amount == 0) {
			products.remove(product);
		} else {
			products.put(product, amount);
		}
	}
	
	public void putToCart(Product product, int amount) {
		int newAmount;
		if(products.containsKey(product)) {
			newAmount = amount + products.get(product);
		} else {
			newAmount = amount;
		}
		
		setProductAmount(product, newAmount);
	}
	
	public void clear() {
		products.clear();
	}
	
}
