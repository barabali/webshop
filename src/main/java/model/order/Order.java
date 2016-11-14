package model.order;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.CombinedOrderItem;
import model.OrderItem;
import model.user.User;

@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private User user;
	@OneToMany 
	private Map<OrderItem, Integer> orderItems;
	@OneToMany
	private Map<CombinedOrderItem, Integer> combinedOrderItems;
	@Enumerated(EnumType.STRING)
	private OrderStatus order;
	@Column(name = "total_price", precision = 7, scale = 2)
	private BigDecimal totalPrice;
	
	public Order(User user, Map<OrderItem, Integer> orderItems, Map<CombinedOrderItem, Integer> combineditems) {
		this.user = user;
		this.orderItems = orderItems;
		this.combinedOrderItems=combineditems;
		this.totalPrice=BigDecimal.valueOf(0.0);
		for (OrderItem orderItem : orderItems.keySet()) {
			totalPrice=totalPrice.add(orderItem.getBasePrice().multiply(BigDecimal.valueOf(orderItems.get(orderItem).doubleValue())));
		}
		for (CombinedOrderItem orderItem : combineditems.keySet()) {
			totalPrice=totalPrice.add(orderItem.getBasePrice().multiply(BigDecimal.valueOf(combineditems.get(orderItem).doubleValue())));
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
	
	public Map<CombinedOrderItem,Integer> getCombinedProducts(){
		return combinedOrderItems;
	}

	public void setProducts(Map<OrderItem, Integer> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

}
