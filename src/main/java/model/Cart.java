package model;


import java.util.HashMap;
import java.util.Map;

import model.order.Order;
import model.user.User;

public class Cart {

	private Long id;
	private User user;
	
	private Map<Product, Integer> products;

	public Cart() {
		this.products = new HashMap<Product, Integer>();
	}
	public Cart(User user){
		this.user=user;
		this.products = new HashMap<Product, Integer>();
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
	
	public Order toOrder() {
		Map<OrderItem,Integer> orders=new HashMap<OrderItem,Integer>();
		for (Product product : products.keySet()) {
			OrderItem order=new OrderItem(product.getName(),product.getBasePrice(),product.getCategory());
			orders.put(order,products.get(product));
		}
		return new Order(user, orders);
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
