package model.order;

import java.math.BigDecimal;
import java.util.Map;

import model.CombinedOrderItem;
import model.OrderItem;
import model.user.User;

public class Order {

	private Long id;
	private User user;
	private Map<OrderItem, Integer> orderItems;
	private Map<CombinedOrderItem, Integer> combinedOrderItems;
	private OrderStatus order;
	private BigDecimal totalPrice;
	
	public Order(User user, Map<OrderItem, Integer> orderItems, Map<CombinedOrderItem, Integer> combineditems) {
		this.user = user;
		this.orderItems = orderItems;
		this.combinedOrderItems=combineditems;
		this.totalPrice=BigDecimal.valueOf(0.0);
		for (OrderItem orderItem : orderItems.keySet()) {
			totalPrice=totalPrice.add(orderItem.getBasePrice().multiply(BigDecimal.valueOf(orderItems.get(orderItem).doubleValue())));
		}
		for (CombinedOrderItem orderItem : combinedOrderItems.keySet()) {
			totalPrice=totalPrice.add(orderItem.getBasePrice().multiply(BigDecimal.valueOf(orderItems.get(orderItem).doubleValue())));
		}
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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
