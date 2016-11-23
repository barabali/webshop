package webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import webshop.model.Category;
import webshop.repository.CategoryRepository;

@Component
public class WebshopRunner implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		categoryRepository.save(new Category("cat1"));
		
		//TODO: insert and query data 
		//1. Create 10 categories, with 100 products in each category
		//2. Create a user
		//3. Create some discounts, of each type
		//4. Create a cart for the user, and put some products, both with and without discount
		//5. Finalize the order		
		//6. List the orders of the user
		
		//7. Connect with visualvm, do CPU profiling, and find 
		//which methods take the most time in the scenario above.
		//If it is possible, try to optimize those methods.
	}

}
