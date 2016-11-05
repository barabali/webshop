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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((discounts == null) ? 0 : discounts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (basePrice == null) {
			if (other.basePrice != null)
				return false;
		} else if (!basePrice.equals(other.basePrice))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (discounts == null) {
			if (other.discounts != null)
				return false;
		} else if (!discounts.equals(other.discounts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	

}
