package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import exception.CartNotFoundException;
import exception.ProductNotFoundException;
import model.Cart;
import model.DailyDiscount;
import model.Discount;
import model.Product;
import model.order.Order;
import repository.CartRepository;
import repository.DailyDiscountRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserDiscountRepository;
import repository.UserRepository;

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
		cartRepository.update(cart);
	}
	
	public void removeFromCart(Cart cart,Product product) {
		cart.setProductAmount(product, 0);
		cartRepository.update(cart);
	}
	
	public BigDecimal calculateFinalPrice(long cartId, long userId) {
		Cart cart = cartRepository.findById(cartId);
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
		Cart cart = cartRepository.findById(cartId);
		Order order = cart.toOrder();
		orderRepository.create(order);
		cart.clear();
		cartRepository.update(cart);
	}
	
	public Map<Product, Integer> showCart(long cartId){
		Cart cart = cartRepository.findById(cartId);
		if(cart==null)
			throw new CartNotFoundException();
		return cart.getProducts();
	}
	
	public void changeNumberInCart(long cartId, long productId,int amount){
		Cart cart = cartRepository.findById(cartId);
		if(cart==null)
			throw new CartNotFoundException();
		Product product = productRepository.findById(productId);
		if(product==null)
			throw new ProductNotFoundException();
		cart.setProductAmount(product, amount);
		cartRepository.update(cart);
	}
	
	public void deleteCart(long cartId){
		Cart cart = cartRepository.findById(cartId);
		cart.clear();
		cartRepository.update(cart);
	}
	
}
