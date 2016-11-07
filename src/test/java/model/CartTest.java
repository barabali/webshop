package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import model.order.Order;
import model.user.User;

public class CartTest {

	static User testuser;
	static Product testProduct;
	static Cart cart;
	static List<Discount> discounts;
	static Discount ten;
	static Discount fourty;

	@BeforeClass
	public static void setUp() {
		testuser = new User("test", "test@test.com", "test", "test");
		testProduct = new Product("abc", BigDecimal.valueOf(20.0), new Category("testCategory"));
		cart = new Cart(testuser);
		discounts = new ArrayList<>();
		ten = new Discount("0.1");
		fourty = new Discount("0.4");
	}

	@Test
	public void testPutToCartNewProduct() {
		cart.putToCart(testProduct, 1);
		Product same = new Product("abc", BigDecimal.valueOf(20.0), new Category("testCategory"));
		Assert.assertEquals(1, cart.getProducts().get(same).intValue());
		Assert.assertEquals(BigDecimal.valueOf(20.0),cart.getTotalPrice());
	}

	@Test
	public void testPutToCartTwoNewSameProduct() {
		cart.putToCart(testProduct, 1);
		Assert.assertEquals(1, cart.getProducts().get(testProduct).intValue());
		Product p = testProduct;
		cart.putToCart(p, 1);
		Assert.assertEquals(2, cart.getProducts().get(testProduct).intValue());
		Assert.assertEquals(BigDecimal.valueOf(40.0),cart.getTotalPrice());
	}

	@Test
	public void testRemoveProductFromCart() {
		cart.putToCart(testProduct, 1);
		cart.setProductAmount(testProduct, 0);
		Assert.assertEquals(null, cart.getProducts().get(testProduct));
		Assert.assertEquals(BigDecimal.valueOf(0.0),cart.getTotalPrice());
	}

	@Test
	public void testCartToOrder() {
		cart.putToCart(testProduct, 1);
		Order order = cart.toOrder();
		Assert.assertEquals(testuser, order.getUser());
		Assert.assertEquals(1, order.getProducts().size());
		Assert.assertEquals(BigDecimal.valueOf(20.0), order.getTotalPrice());
		Assert.assertEquals(BigDecimal.valueOf(20.0), cart.getTotalPrice());
	}

	@Test
	public void testCartTwoDifferentProductToOrder() {
		Product p = new Product("abc", BigDecimal.valueOf(20.0), new Category("testCat"));
		Product p1 = new Product("fa", BigDecimal.valueOf(15.0), new Category("testCat"));
		cart.putToCart(p, 2);
		cart.putToCart(p1, 3);
		Order order = cart.toOrder();
		Assert.assertEquals(testuser, order.getUser());
		Assert.assertEquals(2, order.getProducts().size());
		Assert.assertEquals(BigDecimal.valueOf(85.0), order.getTotalPrice());
		Assert.assertEquals(BigDecimal.valueOf(85.0), cart.getTotalPrice());
	}

	@Test
	public void testOneProductWithOneDiscountToOrder() {
		discounts.add(ten);
		testProduct.setDiscounts(discounts);
		cart.putToCart(testProduct, 1);
		Order order = cart.toOrder();
		Assert.assertEquals(BigDecimal.valueOf(18.0), cart.getTotalPrice());
		Assert.assertEquals(BigDecimal.valueOf(18.0), order.getTotalPrice());
	
	}

	@Test
	public void testOneProductWithTwoDiscountToOrder() {
		discounts.add(ten);
		discounts.add(ten);
		testProduct.setDiscounts(discounts);
		cart.putToCart(testProduct, 1);
		Order order = cart.toOrder();
		Assert.assertEquals(BigDecimal.valueOf(16.0), cart.getTotalPrice());
		Assert.assertEquals(BigDecimal.valueOf(16.0), order.getTotalPrice());
	}

	@Test
	public void testOneProductWithTwoDiscountFourAmountToOrder() {
		discounts.add(ten);
		discounts.add(fourty);
		testProduct.setDiscounts(discounts);
		cart.putToCart(testProduct, 4);
		Order order = cart.toOrder();
		Assert.assertEquals(BigDecimal.valueOf(40.0), cart.getTotalPrice());
		Assert.assertEquals(BigDecimal.valueOf(40.0), order.getTotalPrice());
	}

	@Test
	public void testDifferentProductSameDiscuontToOrder() {
		discounts.add(ten);
		testProduct.setDiscounts(discounts);
		Category sport = new Category("Sport");
		sport.setDiscounts(discounts);
		Product ball = new Product("Ball", BigDecimal.valueOf(500.0), sport);
		ball.setDiscounts(discounts);
		cart.putToCart(ball, 2);
		cart.putToCart(testProduct, 4);
		Order order = cart.toOrder();
		Assert.assertEquals(BigDecimal.valueOf(872.0), cart.getTotalPrice());
		Assert.assertEquals(BigDecimal.valueOf(872.0), order.getTotalPrice());
	}

	@Test
	public void testCartTotalPriceWithOneProduct() {
		discounts.add(ten);
		testProduct.setDiscounts(discounts);
		cart.putToCart(testProduct, 1);
		Assert.assertEquals(BigDecimal.valueOf(18.0), cart.getTotalPrice());
	}

	
	@Test
	public void testCartTotalPriceWithTwoProduct() {
		discounts.add(ten);
		testProduct.setDiscounts(new ArrayList<>(discounts));
		Category car= new Category("Car");
		car.setDiscounts(new ArrayList<>(discounts));
		Product suzuki=new Product("Suzuki",BigDecimal.valueOf(1000),car);
		discounts.add(fourty);
		suzuki.setDiscounts(new ArrayList<>(discounts));
		cart.putToCart(testProduct, 3);
		cart.putToCart(suzuki, 5);
		Assert.assertEquals(BigDecimal.valueOf(2054.0), cart.getTotalPrice());
		Assert.assertEquals(5 ,cart.getProducts().get(suzuki).intValue());
		Assert.assertEquals(3,cart.getProducts().get(testProduct).intValue() );
	}
	

	@After
	public void clearCart() {
		cart.clear();
		testProduct.setDiscounts(new ArrayList<>());
		discounts.clear();
	}

}
