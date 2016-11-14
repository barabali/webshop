package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import exception.CartNotFoundException;
import exception.ProductNotFoundException;
import model.Cart;
import model.Product;
import model.discount.DailyDiscount;
import model.discount.Discount;
import model.order.Order;
import repository.CartRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserRepository;
import repository.discount.DailyDiscountRepository;
import repository.discount.UserDiscountRepository;

public class CartService {

	CartRepository cartRepository;
	OrderRepository orderRepository;
	UserRepository userRepository;
	UserDiscountRepository userDiscountRepository;
	ProductRepository productRepository;
	DailyDiscountRepository dailyDiscountRepository;

	public CartService(CartRepository cartRepository, OrderRepository orderRepository, UserRepository userRepository,
			UserDiscountRepository userDiscountRepository, ProductRepository productRepository, DailyDiscountRepository dailyDiscountRepository) {
		this.cartRepository = cartRepository;
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.userDiscountRepository = userDiscountRepository;
		this.productRepository = productRepository;
		this.dailyDiscountRepository = dailyDiscountRepository;
	}

	public void addToCart(Cart cart,Product product,int amount) {
		cart.putToCart(product, amount);
		cartRepository.save(cart);
	}
	
	public void removeFromCart(Cart cart,Product product) {
		cart.setProductAmount(product, 0);
		cartRepository.save(cart);
	}
	
	public BigDecimal calculateFinalPrice(long cartId, long userId) {
		Cart cart = cartRepository.findOne(cartId);
		if(cart == null)
			throw new CartNotFoundException();
		Order order = cart.toOrder();
		BigDecimal price = order.getTotalPrice();
		BigDecimal totalSpent = userRepository.getSpentMoney(userId);
		Discount userDiscount = userDiscountRepository.findByLimit(totalSpent);
		if(userDiscount != null)
			price = userDiscount.calculateDiscount(price);
		List<DailyDiscount> dailyDiscounts = dailyDiscountRepository.findAll();
		for(DailyDiscount discount : dailyDiscounts) {
			price = discount.calculateDiscount(price);
		}
		return price;
	}
	
	public void finalizeOrder(long cartId){
		Cart cart = cartRepository.findOne(cartId);
		Order order = cart.toOrder();
		orderRepository.save(order);
		cart.clear();
		cartRepository.save(cart);
	}
	
	public Map<Product, Integer> showCart(long cartId){
		Cart cart = cartRepository.findOne(cartId);
		if(cart==null)
			throw new CartNotFoundException();
		return cart.getProducts();
	}
	
	public void changeNumberInCart(long cartId, long productId,int amount){
		Cart cart = cartRepository.findOne(cartId);
		if(cart==null)
			throw new CartNotFoundException();
		Product product = productRepository.findOne(productId);
		if(product==null)
			throw new ProductNotFoundException();
		cart.setProductAmount(product, amount);
		cartRepository.save(cart);
	}
	
	public void deleteCart(long cartId){
		Cart cart = cartRepository.findOne(cartId);
		cart.clear();
		cartRepository.save(cart);
	}
	
}
