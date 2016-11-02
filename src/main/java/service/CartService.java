package service;

import java.util.Map;

import exception.CartNotFoundException;
import model.Cart;
import model.Product;
import model.order.Order;
import model.order.OrderStatus;
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

	public void addToCart(Cart cart,Product product,int amount) {
		cart.putToCart(product, amount);
		cartRepository.update(cart);
	}
	
	public void removeFromCart(Cart cart,Product product) {
		cart.setProductAmount(product, 0);
		cartRepository.update(cart);
	}
	
	public void finalizeOrder(long cartId){
		Cart cart = cartRepository.findById(cartId);
		Order order = cart.toOrder();
		orderRepository.create(order);
		cartRepository.deleteById(cartId);
	}
	
	public Map<Product, Integer> showCart(long cartId){
		Cart cart = cartRepository.findById(cartId);
		if(cart==null)
			throw new CartNotFoundException();
		return cart.getProducts();
	}
	
	public void changeNumberInCart(long cartId,long productId,int amount){
		Cart cart = cartRepository.findById(cartId);
		Product product = productRepository.findById(productId);
		cart.setProductAmount(product, amount);
		cartRepository.update(cart);
	}
	
	public void deleteCart(long cartId){
		Cart cart = cartRepository.findById(cartId);
		cart.clear();
		cartRepository.update(cart);
	}
	
}
