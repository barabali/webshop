package service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.CartNotFoundException;
import exception.ProductNotFoundException;
import model.Cart;
import model.Category;
import model.Day;
import model.Product;
import model.discount.DailyDiscount;
import model.discount.UserDiscount;
import model.order.Order;
import model.user.User;
import repository.CartRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserRepository;
import repository.discount.DailyDiscountRepository;
import repository.discount.UserDiscountRepository;

public class CartServiceTest {

	CartRepository cartRepository;
	CartService cartService;
	ProductRepository productRepository;
	OrderRepository orderRepository;
	UserDiscountRepository userDiscountRepository;
	UserRepository userRepository;
	DailyDiscountRepository dailyDiscountRepository;
	Cart cart;
	Order order;
	List<Order> orders = new ArrayList<Order>();

	@Before
	public void setUp() {
		cartRepository = mock(CartRepository.class);
		productRepository = mock(ProductRepository.class);
		orderRepository = mock(OrderRepository.class);
		userDiscountRepository = mock(UserDiscountRepository.class);
		userRepository = mock(UserRepository.class);
		dailyDiscountRepository = mock(DailyDiscountRepository.class);
		cartService = new CartService(cartRepository, orderRepository, userRepository, userDiscountRepository,
				productRepository, dailyDiscountRepository);
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
	public void testCalculateFinalPriceWithNoUserDiscount() {
		initCartAndOrders();
		when(cartService.cartRepository.findById(0L)).thenReturn(cart);
		when(cartService.userRepository.getSpentMoney(1L)).thenReturn(new BigDecimal(1000));
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(2000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void testCalculateFinalPriceWithUserDiscount() {
		initCartAndOrders();
		UserDiscount discount = new UserDiscount("500", "0.5");
		when(cartService.cartRepository.findById(0L)).thenReturn(cart);
		when(cartService.userRepository.getSpentMoney(1L)).thenReturn(new BigDecimal(1000));
		when(cartService.userDiscountRepository.findByLimit(new BigDecimal(1000))).thenReturn(discount);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(1000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void testCalculateFinalPriceWithUserDiscountOutOfLimits() {
		initCartAndOrders();
		when(cartService.cartRepository.findById(0L)).thenReturn(cart);
		when(cartService.userRepository.getSpentMoney(1L)).thenReturn(new BigDecimal(1000));
		when(cartService.userDiscountRepository.findByLimit(new BigDecimal(1000))).thenReturn(null);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(2000, finalPrice.doubleValue(), 0.01);
	}
	
	@Test
	public void testDailyDiscountMatchingDay() {
		initCartAndOrders();
		DailyDiscount discount = new DailyDiscount("0.5", Day.THURSDAY);
		List<DailyDiscount> discounts = new ArrayList<>();
		discounts.add(discount);
		when(cartService.cartRepository.findById(0L)).thenReturn(cart);
		when(cartService.dailyDiscountRepository.findAll()).thenReturn(discounts);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(1000, finalPrice.doubleValue(), 0.01);
	}
	
	@Test
	public void testDailyDiscountNotMatchingDay() {
		initCartAndOrders();
		DailyDiscount discount = new DailyDiscount("0.5", Day.TUESDAY);
		List<DailyDiscount> discounts = new ArrayList<>();
		discounts.add(discount);
		when(cartService.cartRepository.findById(0L)).thenReturn(cart);
		when(cartService.dailyDiscountRepository.findAll()).thenReturn(discounts);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(2000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void finilizeOrder() {
		initCartAndOrders();
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

	private void initCartAndOrders() {
		cart = new Cart(new User("test", "test@test", "testAddress", "testPass"));
		cart.putToCart(new Product("testProduct", new BigDecimal("2000"), new Category("testCat")), 1);
		Order order = cart.toOrder();
		orders.add(order);
	}

	@After
	public void clearOrders() {
		orders.clear();
	}

}
