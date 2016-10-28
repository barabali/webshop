package model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CartTest {

	
	@Test
	public void testPutToCartPutNewProduct(){
		Cart cart = new Cart();
		cart.putToCart(new Product("abc", BigDecimal.valueOf(20.0)), 1);
		Assert.assertEquals(1, cart.getProducts().get(new Product("abc", BigDecimal.valueOf(20.0))).intValue());
	}
}
