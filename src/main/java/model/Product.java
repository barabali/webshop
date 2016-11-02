package model;

import java.math.BigDecimal;
import java.util.List;

public class Product {

	private Long id;
	private String name;
	private BigDecimal basePrice;
	private List<Discount> discounts;
	private Category category;
	
	public Product() {
	}

	public Product(String name, BigDecimal basePrice,Category cat) {
		this.name = name;
		this.basePrice = basePrice;
		category=cat;
	}


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
	
	public BigDecimal getFinalPrice(){
		BigDecimal finalPrice=basePrice;
		List<Discount> categoryDiscounts=category.getDiscounts();
		
		for (Discount discount : categoryDiscounts) {
			finalPrice=discount.calculateDiscount(finalPrice);
		}
		
		for (Discount discount : discounts) {
			finalPrice=discount.calculateDiscount(finalPrice);
		}
		return finalPrice.setScale(0, 0);
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

	public void setId(Long databaseGeneratedId) {
		id=databaseGeneratedId;
	}

}
