package service;

import model.Cart;
import model.Product;
import model.order.Order;
import repository.CartRepository;
import repository.OrderRepository;
import repository.ProductRepository;

public class CartService {

	private CartRepository cartRepository;
	private ProductRepository productRepository;
	private OrderRepository orderRepository;

	public CartService(CartRepository cartRepository, ProductRepository productRepository, OrderRepository orderRepository) {
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}
	
	public void addToCart(long cartId, long productId, int amount) {
		Cart cart = cartRepository.findById(cartId);
		Product product = productRepository.findById(productId);
		cart.putToCart(product, amount);
		cartRepository.update(cart);
	}
	
	public void removeFromCart(long cartId, long productId) {
		Cart cart = cartRepository.findById(cartId);
		Product product = productRepository.findById(productId);
		cart.setProductAmount(product, 0);
		cartRepository.update(cart);
	}
	
	public void finalizeOrder(long cartId) {
		Cart cart = cartRepository.findById(cartId);
		Order order = cart.toOrder();
		orderRepository.create(order);
		cartRepository.deleteById(cartId);
	}
	
}
