package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "combined_order_item")
public class CombinedOrderItem{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	List<OrderItem> orderItems;
	
	@Column(name = "base_price", precision = 7, scale = 2)
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
