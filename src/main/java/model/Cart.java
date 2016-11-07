package model;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import model.order.Order;
import model.user.User;

public class Cart {

	private Long id;
	private User user;
	private BigDecimal totalPrice;
	
	private Map<Product, Integer> products;

	public Cart() {
		this.products = new HashMap<Product, Integer>();
		this.totalPrice = new BigDecimal("0.0");
	}
	public Cart(User user){
		this.user=user;
		this.products = new HashMap<Product, Integer>();
		this.totalPrice = new BigDecimal("0.0");
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
		reCalculateTotalPrice();
	}

	public Long getId() {
		return id;
	}
	
	public Order toOrder() {
		Map<OrderItem,Integer> orders=new HashMap<OrderItem,Integer>();
		for (Product product : products.keySet()) {
			OrderItem order=new OrderItem(product.getName(),product.getFinalPrice(),product.getCategory());
			orders.put(order,products.get(product));
		}
		return new Order(user, orders);
	}
	
	public void setProductAmount(Product product, int amount) {
		if(amount == 0) {
			products.remove(product);
			reCalculateTotalPrice();
		} else {
			products.put(product, amount);
			reCalculateTotalPrice();
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
		totalPrice=BigDecimal.valueOf(0.0);
	}
	
	private void reCalculateTotalPrice(){
		BigDecimal sum= new BigDecimal("0.0");
		for (Product product : products.keySet()) {
			sum=sum.add(product.getFinalPrice().multiply(BigDecimal.valueOf(products.get(product))));
		}
		totalPrice=sum;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
}
