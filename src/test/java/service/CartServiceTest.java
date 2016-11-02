package service;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import exception.CartNotFoundException;
import exception.ProductNotFoundException;

import org.junit.Assert;
import org.junit.BeforeClass;

import model.Product;
import repository.CartRepository;
import repository.OrderRepository;
import repository.ProductRepository;

public class CartServiceTest {
	
	static CartRepository cartRepository;
	static CartService cartService;
	static ProductRepository productRepository;
	static OrderRepository orderRepository;
	
	@BeforeClass
    public static void setUp() {
		cartRepository = new DummyCartRepository();
		productRepository=new DummyProductRepository();
		orderRepository=new DummyOrderRepository();
		cartService = new CartService(cartRepository,productRepository,orderRepository);
    }
	
	@Test
	public void testDummy(){
		ProductRepository repo=new DummyProductRepository();
		List<Product> test=repo.findAll();
		Assert.assertEquals("test", test.get(0).getName());
	}
	
	@Test
	public void testShowCartExists() throws CartNotFoundException{
		Map<Product, Integer> resultValue=cartService.showCart(0);
		Assert.assertNotNull(resultValue);
	}
	
	@Test(expected = CartNotFoundException.class)
	public void testShowNotCartNotExists() throws CartNotFoundException{
		Map<Product, Integer> resultValue=cartService.showCart(1);
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void changeNumberInCart() throws CartNotFoundException{
		cartService.changeNumberInCart(0,0,2);
	}
}
