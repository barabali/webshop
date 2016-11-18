package webshop.service;


import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import webshop.model.Category;
import webshop.model.Day;
import webshop.model.Product;
import webshop.model.discount.DailyDiscount;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;
import webshop.repository.discount.DailyDiscountRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIT {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	DailyDiscountRepository dailyDiscountRepository;
	
	Category cat;
	
	@Before
	public void setUp(){
		categoryRepository.save(new Category("testCat"));
		cat=categoryRepository.findByName("testCat");
		
		productRepository.save(new Product("testProduct",BigDecimal.valueOf(1000),cat));
	}
	
	@Test
	public void testFindByName(){		
		List<Product> products=productRepository.findByName("testProduct");
		assertNotNull(products);
	}
	
	@Test
	public void testFindByCategoryId(){
		List<Product> products=productRepository.findByCategoryId(cat.getId());
		assertNotNull(products);
	}
	
	@Test
	public void testFindByBasePriceLessThan(){
		List<Product> products=productRepository.findByBasePriceLessThan(BigDecimal.valueOf(2000));
		assertNotNull(products);
	}
	
	@Test
	public void testdeleteByCategoryId(){
		productRepository.deleteByCategoryId(cat.getId());
		List<Product> products=productRepository.findAll();
		Assert.assertEquals(0,products.size());
	}
	
	@Test
	public void testDailyDiscountRepo(){
		DailyDiscount disc=new DailyDiscount("10",Day.MONDAY);
		dailyDiscountRepository.save(disc);
		List<DailyDiscount> dailyDiscounts = dailyDiscountRepository.findAll();
		Assert.assertEquals(1,dailyDiscounts.size());
	}
	
	
}
