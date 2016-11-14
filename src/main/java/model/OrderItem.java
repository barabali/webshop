package model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "base_price", precision = 7, scale = 2)
	private BigDecimal basePrice;
	@ManyToOne
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public OrderItem(String name, BigDecimal basePrice, Category category) {
		this.name = name;
		this.basePrice = basePrice;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Long getId() {
		return id;
	}

}
