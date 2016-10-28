package model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CartTest {
	

	
	@Test
	public void testPutToCartPutNewProduct(){
		Cart cart = new Cart();
		Product p= new Product("abc", BigDecimal.valueOf(20.0));
		cart.putToCart(p, 1);
		Assert.assertEquals(1, cart.getProducts().get(p).intValue());
	}
	
	@Test
	public void testPutTocartPutTwoNewProduct(){
		Cart cart = new Cart();
		Product p= new Product("abc", BigDecimal.valueOf(20.0));
		cart.putToCart(p, 1);
		Assert.assertEquals(1, cart.getProducts().get(p).intValue());
		cart.putToCart(p, 1);
		Assert.assertEquals(2, cart.getProducts().get(p).intValue());
	}
}
