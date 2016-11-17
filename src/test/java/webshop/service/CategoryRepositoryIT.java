package webshop.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import webshop.model.Category;
import webshop.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIT {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void testFindAll(){
		categoryRepository.save(new Category("Sport"));
		List<Category> categories=categoryRepository.findAll();
		assertNotNull(categories);
		
	}
	
	@Test
	public void testFindByName(){
		categoryRepository.save(new Category("Sport"));
		Category sp= categoryRepository.findByName("Sport");
		assertNotNull(sp);
	}

}
