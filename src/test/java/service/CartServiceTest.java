package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

import exception.CartNotFoundException;
import exception.ProductNotFoundException;
import model.Cart;
import model.Product;
import model.user.User;
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
		productRepository = new DummyProductRepository();
		orderRepository = new DummyOrderRepository();
		cartService = new CartService(cartRepository, productRepository, orderRepository);
	}

	@Test
	public void testDummy() {
		ProductRepository repo = new DummyProductRepository();
		List<Product> test = repo.findAll();
		Assert.assertEquals("test", test.get(0).getName());
	}

	@Test
	public void testShowCartExists() throws CartNotFoundException {
		cartService.cartRepository = mock(CartRepository.class);
		
		when(cartService.cartRepository.findById(1L)).thenReturn(new Cart());
		
		Map<Product, Integer> resultValue = cartService.showCart(1L);
		Assert.assertNotNull(resultValue);
	}

	@Test(expected = CartNotFoundException.class)
	public void testShowNotCartNotExists() throws CartNotFoundException {
		cartService.cartRepository = mock(CartRepository.class);
		
		when(cartService.cartRepository.findById(1L)).thenReturn(null);
		
		Map<Product, Integer> resultValue = cartService.showCart(1L);
	}

	@Test(expected = ProductNotFoundException.class)
	public void changeNumberInCart() throws CartNotFoundException {
		cartService.changeNumberInCart(0, 0, 2);
	}

	@Test
	public void finilizeOrder() {
		cartService.finalizeOrder(0);
		String[] expected = { "test", "test@test", "testAddress", "testPass" };
		User actualUser = orderRepository.findAll().get(0).getUser();
		String[] actual = { actualUser.getName(), actualUser.getEmail(), actualUser.getAddress(),
				actualUser.getPassword() };
		Assert.assertArrayEquals(expected, actual);
		Assert.assertEquals(BigDecimal.valueOf(2000.0), orderRepository.findAll().get(0).getTotalPrice());
	}

}
