package service;

import model.Category;
import model.Product;
import model.discount.Discount;
import repository.CategoryRepository;
import repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public void deleteCategory(long categoryId){
		productRepository.deleteByCategoryId(categoryId);
		categoryRepository.deleteById(categoryId);
	}

	public void addDiscount(Product product, Discount discount) {
		product.addDiscount(discount);
		productRepository.update(product);
	}
	
	public void addCategoryDiscount(Category category, Discount discount) {
		category.addDiscount(discount);
		categoryRepository.update(category);
	}
	
}
