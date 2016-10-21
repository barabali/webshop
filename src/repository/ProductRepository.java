package repository;

import java.util.List;

import model.Product;

public interface ProductRepository extends Repository<Product>{

	public List<Product> findByCategoryId(long categoryId);
	
}
