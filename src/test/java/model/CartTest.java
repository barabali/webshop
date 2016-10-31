package model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import model.order.Order;
import model.user.User;

public class CartTest {
	
	static User testuser;
	
    @BeforeClass
    public static void setUp() {
    	testuser=new User("test","test@test.com","test","test");
    }
	
	@Test
	public void testPutToCartPutNewProduct(){
		Cart cart = new Cart(testuser);
		Product p= new Product("abc", BigDecimal.valueOf(20.0));
		cart.putToCart(p, 1);
		Assert.assertEquals(1, cart.getProducts().get(p).intValue());
	}
	
	@Test
	public void testPutTocartPutTwoNewSameProduct(){
		Cart cart = new Cart(testuser);
		Product p= new Product("abc", BigDecimal.valueOf(20.0));
		cart.putToCart(p, 1);
		Assert.assertEquals(1, cart.getProducts().get(p).intValue());
		cart.putToCart(p, 1);
		Assert.assertEquals(2, cart.getProducts().get(p).intValue());
	}
	
	@Test
	public void testRemoveProductFromCart(){
		Cart cart = new Cart(testuser);
		Product p= new Product("abc", BigDecimal.valueOf(20.0));
		cart.putToCart(p, 1);
		cart.setProductAmount(p, 0);
		Assert.assertEquals(null, cart.getProducts().get(p));
	}
	
	@Test
	public void testCartToOrder(){
		Cart cart = new Cart(testuser);
		Product p= new Product("abc", BigDecimal.valueOf(20.0));
		cart.putToCart(p, 1);
		Order order=cart.toOrder();
		Assert.assertEquals(testuser,order.getUser());
		Assert.assertEquals(1,order.getProducts().size());
	}
	
	
}
