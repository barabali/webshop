package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CombinedProduct{
	private Long id;
	private List<Product> products;
	private BigDecimal fixedPrice;

	public CombinedProduct(Product a, Product b, BigDecimal bigDecimal) {
		products=new ArrayList<>();
		products.add(a);
		products.add(b);
		fixedPrice=bigDecimal;		
	}
	
	public CombinedProduct(Product a, Product b,Product c, BigDecimal bigDecimal) {
		products=new ArrayList<>();
		products.add(a);
		products.add(b);
		products.add(c);
		fixedPrice=bigDecimal;		
	}

	public BigDecimal getPrice() {
		return fixedPrice;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long databaseGeneratedId) {
		id = databaseGeneratedId;
	}

	public List<Product> getProducts() {
		return products;
	}

}
