package webshop.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import webshop.model.order.Order;
import webshop.model.user.User;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
	@Column(name = "base_price", precision = 7, scale = 2)
	private BigDecimal totalPrice;
	
	@ElementCollection
	private Map<Product, Integer> products;
	
	@ElementCollection
	private Map<CombinedProduct,Integer> productsCombined;;

	public Cart() {
		this.products = new HashMap<Product, Integer>();
		this.productsCombined = new HashMap<CombinedProduct, Integer>();
		this.totalPrice = new BigDecimal("0.0");
	}
	public Cart(User user){
		this.user=user;
		this.products = new HashMap<Product, Integer>();
		this.productsCombined = new HashMap<CombinedProduct, Integer>();
		this.totalPrice = new BigDecimal("0.0");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
		reCalculateTotalPrice();
	}

	public Long getId() {
		return id;
	}
	
	public Order toOrder() {
		Order o=new Order(user, productsToOrder(),combinedProductsToOrder());
		return o;
	}
	
	private Map<OrderItem,Integer> productsToOrder(){
		Map<OrderItem,Integer> orders=new HashMap<OrderItem,Integer>();
		for (Product product : products.keySet()) {
			OrderItem order=new OrderItem(product.getName(),product.getFinalPrice(),product.getCategory());
			orders.put(order,products.get(product));
		}
		return orders;
	}
	
	private Map<CombinedOrderItem,Integer> combinedProductsToOrder(){
		Map<CombinedOrderItem,Integer> orders=new HashMap<CombinedOrderItem,Integer>();
		
		for (CombinedProduct combinedProduct : productsCombined.keySet()) {		
			List<Product> productsfromcombined=combinedProduct.getProducts();
			
			List<OrderItem> items=extractOrderItems(productsfromcombined);
			
			CombinedOrderItem order=new CombinedOrderItem(items,combinedProduct.getPrice());			
			orders.put(order,productsCombined.get(combinedProduct));			
		}
		return orders;
	}
	
	private List<OrderItem> extractOrderItems(List<Product> productsfromcombined) {
		List<OrderItem> list=new ArrayList<>();
		for (Product product : productsfromcombined) {
			OrderItem order=new OrderItem(product.getName(),product.getFinalPrice(),product.getCategory());
			list.add(order);
		}
		return list;
	}
	public void putToCart(Product product, int amount) {
		int newAmount;
		if(products.containsKey(product)) {
			newAmount = amount + products.get(product);
		} else {
			newAmount = amount;
		}
		
		setProductAmount(product, newAmount);
	}
	
	public void setProductAmount(Product product, int amount) {
		if(amount == 0) {
			products.remove(product);
			reCalculateTotalPrice();
		} else {
			products.put(product, amount);
			reCalculateTotalPrice();
		}
	}
	
	public void putToCart(CombinedProduct product, int amount) {
		int newAmount;
		if(productsCombined.containsKey(product)) {
			newAmount = amount + productsCombined.get(product);
		} else {
			newAmount = amount;
		}
		
		setProductCombinedAmount(product, newAmount);
	}
	
	public void setProductCombinedAmount(CombinedProduct product, int amount) {
		if(amount == 0) {
			productsCombined.remove(product);
			reCalculateTotalPrice();
		} else {
			productsCombined.put(product, amount);
			reCalculateTotalPrice();
		}
	}
	
	
	public void clear() {
		products.clear();
		productsCombined.clear();
		totalPrice=BigDecimal.valueOf(0.0);
	}
	
	private void reCalculateTotalPrice(){
		totalPrice=sumProducts().add(sumCombinedProducts());
	}
	
	private BigDecimal sumProducts() {
		BigDecimal sum= new BigDecimal("0.0");
		for (Product product : products.keySet()) {
			sum=sum.add(product.getFinalPrice().multiply(BigDecimal.valueOf(products.get(product))));
		}
		return sum;
	}
	
	private BigDecimal sumCombinedProducts() {
		BigDecimal sum= new BigDecimal("0.0");
		for(CombinedProduct combined : productsCombined.keySet()){
			sum=sum.add(combined.getPrice().multiply(BigDecimal.valueOf(productsCombined.get(combined))));
		}
		return sum;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
}
