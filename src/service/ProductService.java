package service;

import java.util.List;

import model.Product;
import repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
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

	
}
