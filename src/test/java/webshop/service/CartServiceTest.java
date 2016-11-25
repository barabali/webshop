package webshop.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import webshop.exception.CartNotFoundException;
import webshop.exception.ProductNotFoundException;
import webshop.model.Cart;
import webshop.model.Category;
import webshop.model.Day;
import webshop.model.Product;
import webshop.model.discount.DailyDiscount;
import webshop.model.discount.UserDiscount;
import webshop.model.order.Order;
import webshop.model.user.User;
import webshop.repository.CartRepository;
import webshop.repository.CombinedOrderItemRepository;
import webshop.repository.OrderItemRepository;
import webshop.repository.OrderRepository;
import webshop.repository.ProductRepository;
import webshop.repository.UserRepository;
import webshop.repository.discount.DailyDiscountRepository;
import webshop.repository.discount.UserDiscountRepository;
import webshop.service.CartService;

public class CartServiceTest {

	@InjectMocks
	CartService cartService;

	@Mock
	CartRepository cartRepository;
	@Mock
	ProductRepository productRepository;
	@Mock
	OrderRepository orderRepository;
	@Mock
	UserDiscountRepository userDiscountRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	DailyDiscountRepository dailyDiscountRepository;
	@Mock
	OrderItemRepository orderItemRepository;
	@Mock
	CombinedOrderItemRepository combinedOrderItemRepository;

	Cart cart;
	Order order;
	List<Order> orders = new ArrayList<Order>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShowCartExists() throws CartNotFoundException {
		when(cartService.cartRepository.findOne(1L)).thenReturn(new Cart());
		Map<Product, Integer> resultValue = cartService.showCart(1L);
		Assert.assertNotNull(resultValue);
	}

	@Test(expected = CartNotFoundException.class)
	public void testShowNotCartNotExists() throws CartNotFoundException {
		when(cartService.cartRepository.findOne(1L)).thenReturn(null);
		cartService.showCart(1L);
	}

	@Test(expected = ProductNotFoundException.class)
	public void changeNumberInCart() throws CartNotFoundException {
		when(cartService.cartRepository.findOne(0L)).thenReturn(new Cart());
		when(cartService.productRepository.findOne(1L)).thenReturn(null);
		cartService.changeNumberInCart(0, 0, 2);
	}

	@Test
	public void testCalculateFinalPriceWithNoUserDiscount() {
		initCartAndOrders();
		when(cartService.cartRepository.findOne(0L)).thenReturn(cart);
		when(cartService.userRepository.getSpentMoney(1L)).thenReturn(new BigDecimal(1000));
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(2000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void testCalculateFinalPriceWithUserDiscount() {
		initCartAndOrders();
		UserDiscount discount = new UserDiscount("500", "0.5");
		when(cartService.cartRepository.findOne(0L)).thenReturn(cart);
		when(cartService.userRepository.getSpentMoney(1L)).thenReturn(new BigDecimal(1000));
		when(cartService.userDiscountRepository.findFirstByLimitGreaterThanOrderByLimit(new BigDecimal(1000)))
				.thenReturn(discount);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(1000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void testCalculateFinalPriceWithUserDiscountOutOfLimits() {
		initCartAndOrders();
		when(cartService.cartRepository.findOne(0L)).thenReturn(cart);
		when(cartService.userRepository.getSpentMoney(1L)).thenReturn(new BigDecimal(1000));
		when(cartService.userDiscountRepository.findFirstByLimitGreaterThanOrderByLimit(new BigDecimal(1000)))
				.thenReturn(null);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(2000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void testDailyDiscountMatchingDay() {
		initCartAndOrders();
		Calendar cal = Calendar.getInstance();
		Day day = Day.fromCalendar(cal.get(Calendar.DAY_OF_WEEK));
		DailyDiscount discount = new DailyDiscount("0.5", day);
		List<DailyDiscount> discounts = new ArrayList<>();
		discounts.add(discount);
		when(cartService.cartRepository.findOne(0L)).thenReturn(cart);
		when(cartService.dailyDiscountRepository.findAll()).thenReturn(discounts);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(1000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void testDailyDiscountNotMatchingDay() {
		initCartAndOrders();
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DAY_OF_WEEK, -1);
		Day day = Day.fromCalendar(cal.get(Calendar.DAY_OF_WEEK));
		DailyDiscount discount = new DailyDiscount("0.5", day);
		List<DailyDiscount> discounts = new ArrayList<>();
		discounts.add(discount);
		when(cartService.cartRepository.findOne(0L)).thenReturn(cart);
		when(cartService.dailyDiscountRepository.findAll()).thenReturn(discounts);
		BigDecimal finalPrice = cartService.calculateFinalPrice(0L, 1L);
		Assert.assertEquals(2000, finalPrice.doubleValue(), 0.01);
	}

	@Test
	public void finilizeOrder() {
		initCartAndOrders();
		when(cartService.cartRepository.findOne(0L)).thenReturn(cart);
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
