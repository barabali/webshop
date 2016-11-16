package webshop.model.order;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import webshop.model.CombinedOrderItem;
import webshop.model.OrderItem;
import webshop.model.user.User;

@Entity
@Table(name = "`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private User user;
	
	@ElementCollection
	private Map<OrderItem, Integer> orderItems;
	
	@ElementCollection
	private Map<CombinedOrderItem, Integer> combinedOrderItems;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
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
		return orderStatus;
	}

	public void setOrder(OrderStatus order) {
		this.orderStatus = order;
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
