package webshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import webshop.model.Cart;
import webshop.model.Category;
import webshop.model.CombinedProduct;
import webshop.model.Day;
import webshop.model.Product;
import webshop.model.discount.DailyDiscount;
import webshop.model.discount.Discount;
import webshop.model.discount.TimedDiscount;
import webshop.model.discount.UserDiscount;
import webshop.model.order.Order;
import webshop.model.user.User;
import webshop.repository.CartRepository;
import webshop.repository.CategoryRepository;
import webshop.repository.CombinedProductRepository;
import webshop.repository.OrderRepository;
import webshop.repository.ProductRepository;
import webshop.repository.UserRepository;
import webshop.repository.discount.DailyDiscountRepository;
import webshop.repository.discount.DiscountRepository;
import webshop.repository.discount.TimedDiscountRepository;
import webshop.repository.discount.UserDiscountRepository;
import webshop.service.CartService;

@Component
public class WebshopRunner implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CartService cartService;
	@Autowired
	DiscountRepository discountRepository;
	@Autowired
	CombinedProductRepository combinedProductRepository;
	@Autowired
	DailyDiscountRepository dailyDiscountRepository;
	@Autowired
	TimedDiscountRepository timedDiscountRepository;
	@Autowired
	UserDiscountRepository userDiscountRepository;

	static final int CATEGORYSIZE = 10;
	static final int PRODUCTSIZE = 100;

	@Override
	public void run(String... args) throws Exception {
		if (categoryRepository.count() == 0)
			createCategoriesWithProducts();
		if (userRepository.count() == 0)
			createUser();
		if (cartRepository.count() == 0)
			createCartWithProducts();
		if (orderRepository.count() == 0)
			finalizeOrders();
		if(discountRepository.count()== 0)
			createEachDiscount();
		
		listOrders();
						

		// TODO: insert and query data
		// 1. Create 10 categories, with 100 products in each category
		// 2. Create a user
		// 3. Create some discounts, of each type
		// 4. Create a cart for the user, and put some products, both with and
		// without discount
		// 5. Finalize the order
		// 6. List the orders of the user

		// 7. Connect with visualvm, do CPU profiling, and find
		// which methods take the most time in the scenario above.
		// If it is possible, try to optimize those methods.
	}

	// 1
	private void createCategoriesWithProducts() {
		Random rand = new Random();
		for (int i = 0; i < CATEGORYSIZE; i++) {
			Category cat = new Category("cat" + i);
			categoryRepository.save(cat);
			for (int j = 0; j < PRODUCTSIZE; j++) {
				int price = rand.nextInt(1000) + 1;
				Product p = new Product(i + "_product" + j, BigDecimal.valueOf(price), cat);
				productRepository.save(p);
			}
		}
	}

	// 2
	private void createUser() {
		User test = new User("Name", "mail@test.com", "addres", "password");
		userRepository.save(test);
	}
	
	//3
	private void createEachDiscount(){
		//Create discounts
		Discount basicDisc=new Discount("0.3");
		DailyDiscount dailyDisc=new DailyDiscount("0.1",Day.MONDAY);
		TimedDiscount timedDisc=createTimedDiscount();
		
		saveDiscounts(basicDisc, dailyDisc, timedDisc);
		
		//Create test category
		Category discTestCategory=new Category("discTestCat");
		
		//Add discount to category
		discTestCategory.addDiscount(basicDisc);
		//Save category
		categoryRepository.save(discTestCategory);
		
		saveProductWithDiscounts(basicDisc, dailyDisc, timedDisc, discTestCategory);
		
		createCombinedProduct();
		
		createUserDiscount();
	}

	private void saveDiscounts(Discount basicDisc, DailyDiscount dailyDisc, TimedDiscount timedDisc) {
		dailyDiscountRepository.save(dailyDisc);
		timedDiscountRepository.save(timedDisc);
		discountRepository.save(basicDisc);
	}

	private void saveProductWithDiscounts(Discount basicDisc, DailyDiscount dailyDisc, TimedDiscount timedDisc,
			Category discTestCategory) {
		
		Product testProduct=new Product("discTest",BigDecimal.valueOf(1000),discTestCategory);
		
		testProduct.addDiscount(dailyDisc);
		testProduct.addDiscount(basicDisc);
		testProduct.addDiscount(timedDisc);
		
		productRepository.save(testProduct);
	}

	private void createCombinedProduct() {
		Product p1 = productRepository.findOne(1L);
		Product p2 = productRepository.findOne(2L);
		CombinedProduct combinedDisc=new CombinedProduct(p1, p2, BigDecimal.valueOf(10000));
		combinedProductRepository.save(combinedDisc);
	}

	private void createUserDiscount() {
		UserDiscount userD=new UserDiscount();
		userD.setValue(BigDecimal.valueOf(0.1));
		userD.setLimit(BigDecimal.valueOf(10000));
		userDiscountRepository.save(userD);
	}
	
	private TimedDiscount createTimedDiscount(){
		Date now = new Date();
		Date before = (Date) now.clone();
		before.setYear(now.getYear() - 1);
		Date after = (Date) now.clone();
		after.setYear(now.getYear() + 1);
		return new TimedDiscount("0.3", before, after);
	}

	private List<Discount> createDailyDiscount() {
		List<Discount> discounts = new ArrayList<>();
		discounts.add(new DailyDiscount("0.1", Day.MONDAY));
		discounts.add(new DailyDiscount("0.3", Day.TUESDAY));
		discounts.add(new DailyDiscount("0.15", Day.WEDNESDAY));
		discounts.add(new DailyDiscount("0.05", Day.THURSDAY));
		discounts.add(new DailyDiscount("0.12", Day.FRIDAY));
		discounts.add(new DailyDiscount("0.2", Day.SATURDAY));
		discounts.add(new DailyDiscount("0.1", Day.SUNDAY));
		return discounts;
	}

	// 4
	private void createCartWithProducts() {
		User user = userRepository.findByEmail("mail@test.com");
		Cart cart = new Cart(user);

		Product p1 = productRepository.findOne(1L);
		Product p2 = productRepository.findOne(2L);
		Product p3 = productRepository.findOne(3L);
		Product p4 = productRepository.findOne(4L);
		Product p5 = productRepository.findOne(5L);

		cart.putToCart(p1, 2);
		cart.putToCart(p2, 5);
		cart.putToCart(p3, 10);
		cart.putToCart(p4, 1);
		cart.putToCart(p5, 1);

		cartRepository.save(cart);
		
	}

	// 5
	private void finalizeOrders() {
		Cart cart = cartRepository.findByUserEmail("mail@test.com");
		cartService.finalizeOrder(cart.getId());
	}

	// 6
	private void listOrders() {
		List<Order> orders = orderRepository.findByUserEmail("mail@test.com");
	}

}
