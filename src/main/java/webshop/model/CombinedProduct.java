package webshop.model;

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
@Table (name = "combined_product")
public class CombinedProduct{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	private List<Product> products;
	
	@Column(name = "base_price", precision = 7, scale = 2)
	private BigDecimal fixedPrice;
	
	public CombinedProduct(){
		products=new ArrayList<>();
		fixedPrice=new BigDecimal(0);
	}

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
	
	public void setPrice(BigDecimal value){
		fixedPrice=value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fixedPrice == null) ? 0 : fixedPrice.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
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
		CombinedProduct other = (CombinedProduct) obj;
		if (fixedPrice == null) {
			if (other.fixedPrice != null)
				return false;
		} else if (!fixedPrice.equals(other.fixedPrice))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
	
	

}
