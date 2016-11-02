package service;

import java.util.List;

import org.junit.Test;

import org.junit.Assert;
import model.Product;
import repository.ProductRepository;

public class CartServiceTest {
	
	@Test
	public void testDummy(){
		ProductRepository repo=new DummyProductRepository();
		List<Product> test=repo.findAll();
		Assert.assertEquals("test", test.get(0).getName());
	}
}
