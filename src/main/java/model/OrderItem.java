package model;

import java.math.BigDecimal;

public class OrderItem {

	private Long id;
	private String name;
	private BigDecimal basePrice;
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
