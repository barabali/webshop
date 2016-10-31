package model.order;

import java.math.BigDecimal;
import java.util.Map;
import model.OrderItem;
import model.user.User;

public class Order {

	private Long id;
	private User user;
	private Map<OrderItem, Integer> orderItems;
	private OrderStatus order;
	private BigDecimal totaPrice;
	
	public Order(User user, Map<OrderItem, Integer> orderItems) {
		this.user = user;
		this.orderItems = orderItems;
	}
	
	public BigDecimal getTotaPrice() {
		return totaPrice;
	}

	public void setTotaPrice(BigDecimal totaPrice) {
		this.totaPrice = totaPrice;
	}

	public OrderStatus getOrder() {
		return order;
	}

	public void setOrder(OrderStatus order) {
		this.order = order;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<OrderItem, Integer> getProducts() {
		return orderItems;
	}

	public void setProducts(Map<OrderItem, Integer> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

}
