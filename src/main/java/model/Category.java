package model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private Long id;
	private String name;
	private Discount discount;
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category(String name) {
		this.name = name;
		discount=new Discount("0.0");
		products=new ArrayList<Product>();
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
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
