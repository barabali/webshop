package service;

import java.math.BigDecimal;
import java.util.List;

import model.Cart;
import model.Category;
import model.Product;
import model.user.User;
import repository.CartRepository;

public class DummyCartRepository implements CartRepository {

	@Override
	public void create(Cart entity) {

	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public void update(Cart entity) {

	}

	@Override
	public Cart findById(long id) {
		if (id == 0) {
			Cart result = new Cart(new User("test", "test@test", "testAddress", "testPass"));
			result.putToCart(new Product("testProduct", new BigDecimal("2000"), new Category("testCat")), 1);
			return result;
		} else
			return null;
	}

	@Override
	public List<Cart> findAll() {
		return null;
	}

}
