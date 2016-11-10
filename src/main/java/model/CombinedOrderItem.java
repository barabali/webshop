package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CombinedOrderItem{
	private Long id;
	
	List<OrderItem> orderItems;
	
	private BigDecimal basePrice;

	
	public CombinedOrderItem(List<OrderItem> items, BigDecimal price) {
		orderItems=new ArrayList<>();
		this.basePrice = price;
	}

	public List<OrderItem> getOrderItems(){
		return orderItems;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Long getId() {
		return id;
	}
}
