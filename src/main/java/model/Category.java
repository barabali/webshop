package model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private Long id;
	private String name;
	private List<Discount> discounts;
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category(String name) {
		this.name = name;
		discounts=new ArrayList<>();
		products=new ArrayList<Product>();
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}
