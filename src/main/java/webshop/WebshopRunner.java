package webshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import webshop.model.Cart;
import webshop.model.Category;
import webshop.model.Day;
import webshop.model.Product;
import webshop.model.discount.DailyDiscount;
import webshop.model.discount.Discount;
import webshop.model.user.User;
import webshop.repository.CartRepository;
import webshop.repository.CategoryRepository;
import webshop.repository.OrderRepository;
import webshop.repository.ProductRepository;
import webshop.repository.UserRepository;
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

	static final int CATEGORYSIZE = 10;
	static final int PRODUCTSIZE = 100;

	@Override
	public void run(String... args) throws Exception {
		if (categoryRepository.count() == 0)
			createCategoriesWithProducts();
		if (userRepository.count() == 0)
			createUser();
		if(cartRepository.count() == 0)
			createCartWithProducts();
		if(orderRepository.count() == 0)
			finalizeOrders();

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

	
	//2
	private void createUser() {
		User test = new User("Name", "mail@test.com", "addres", "password");
		userRepository.save(test);
	}

	//1
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
	
	//4
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
	
	//5
	private void finalizeOrders() {
		cartService.finalizeOrder(3L);
	}

}
