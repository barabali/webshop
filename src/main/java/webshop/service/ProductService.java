package webshop.service;

import webshop.model.Category;
import webshop.model.Product;
import webshop.model.discount.Discount;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public void deleteCategory(long categoryId){
		productRepository.deleteByCategoryId(categoryId);
		categoryRepository.delete(categoryId);
	}

	public void addDiscount(Product product, Discount discount) {
		product.addDiscount(discount);
		productRepository.save(product);
	}
	
	public void addCategoryDiscount(Category category, Discount discount) {
		category.addDiscount(discount);
		categoryRepository.save(category);
	}
	
}
