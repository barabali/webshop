package service;

import java.math.BigDecimal;
import java.util.List;

import model.Category;
import model.Discount;
import model.Product;
import repository.CategoryRepository;
import repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<Product> listProductsInCategory(long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}
	
	public List<Product> searchProductByName(String expression){
		return productRepository.searchByName(expression);
	}
	
	public List<Product> searchProductByMinMax(int min,int max){
		return productRepository.searchByMinMax(min,max);
	}
	
	public void createCategory(String name,Discount discount){
		Category new_category=new Category(name);
		new_category.setDiscount(discount);
		categoryRepository.create(new_category);
	}
	
	public void createProduct(String name,Category category,BigDecimal basePrice){
		Product new_product=new Product();
		new_product.setBasePrice(basePrice);
		new_product.setCategory(category);
		new_product.setName(name);
		productRepository.create(new_product);
	}
	
	public void modifyCategory(long category_id,String new_category_name,Discount discount){
		Category category=categoryRepository.findById(category_id);
		category.setName(new_category_name);
		category.setDiscount(discount);
		categoryRepository.update(category);
	}
	
	public void modifyProduct(long product_id,String name,BigDecimal basePrice,List<Discount> discounts,Category category){
		Product product=productRepository.findById(product_id);
		product.setBasePrice(basePrice);
		product.setCategory(category);
		product.setDiscounts(discounts);
		product.setName(name);
		productRepository.update(product);
	}
	
	//Jelenleg törli az összes teméket ami benne volt, ez nem volt specifikálva hogy legyen
	public void deleteCategory(long categoryId){
		productRepository.deleteByCategoryId(categoryId);
		categoryRepository.deleteById(categoryId);
	}
	
	public void deleteProduct(long product_id){
		productRepository.deleteById(product_id);
	}

	
}
