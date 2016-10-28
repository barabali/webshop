package model;

import java.math.BigDecimal;
import java.util.List;

public class Product {

	private Long id;
	private String name;
	private BigDecimal basePrice;
	
	public Product() {
	}

	public Product(String name, BigDecimal basePrice) {
		this.name = name;
		this.basePrice = basePrice;
	}

	private List<Discount> discounts;
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

}
