package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import exception.CartNotFoundException;
import exception.ProductNotFoundException;
import model.Cart;
import model.Category;
import model.Product;
import model.order.Order;
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
		cartRepository = mock(CartRepository.class);
		productRepository = mock(ProductRepository.class);
		orderRepository = mock(OrderRepository.class);
		cartService = new CartService(cartRepository, productRepository, orderRepository);
	}

	@Test
	public void testShowCartExists() throws CartNotFoundException {
		when(cartService.cartRepository.findById(1L)).thenReturn(new Cart());
		Map<Product, Integer> resultValue = cartService.showCart(1L);
		Assert.assertNotNull(resultValue);
	}

	@Test(expected = CartNotFoundException.class)
	public void testShowNotCartNotExists() throws CartNotFoundException {
		when(cartService.cartRepository.findById(1L)).thenReturn(null);
		cartService.showCart(1L);
	}

	@Test(expected = ProductNotFoundException.class)
	public void changeNumberInCart() throws CartNotFoundException {
		when(cartService.cartRepository.findById(0)).thenReturn(new Cart());
		when(cartService.productRepository.findById(1L)).thenReturn(null);
		cartService.changeNumberInCart(0, 0, 2);
	}

	@Test
	public void finilizeOrder() {
		Cart cart = new Cart(new User("test", "test@test", "testAddress", "testPass"));
		cart.putToCart(new Product("testProduct", new BigDecimal("2000"), new Category("testCat")), 1);
		Order order = cart.toOrder();
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		when(cartService.cartRepository.findById(0L)).thenReturn(cart);
		when(cartService.orderRepository.findAll()).thenReturn(orders);
		cartService.finalizeOrder(0L);
		String[] expected = { "test", "test@test", "testAddress", "testPass" };
		User actualUser = cartService.orderRepository.findAll().get(0).getUser();
		String[] actual = { actualUser.getName(), actualUser.getEmail(), actualUser.getAddress(),
				actualUser.getPassword() };
		Assert.assertArrayEquals(expected, actual);
		Assert.assertEquals(BigDecimal.valueOf(2000.0), cartService.orderRepository.findAll().get(0).getTotalPrice());
	}

}
