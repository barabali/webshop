package webshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webshop.exception.CartNotFoundException;
import webshop.exception.ProductNotFoundException;
import webshop.model.Cart;
import webshop.model.Product;
import webshop.model.discount.DailyDiscount;
import webshop.model.discount.Discount;
import webshop.model.order.Order;
import webshop.repository.CartRepository;
import webshop.repository.OrderRepository;
import webshop.repository.ProductRepository;
import webshop.repository.UserRepository;
import webshop.repository.discount.DailyDiscountRepository;
import webshop.repository.discount.UserDiscountRepository;

//TODO: @Service for all service class
@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	//TODO: autowire all repositories, in all service classes
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDiscountRepository userDiscountRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
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
	
	@Transactional
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
