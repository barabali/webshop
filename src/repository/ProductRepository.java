package repository;

import java.util.List;

import model.Product;

public interface ProductRepository extends Repository<Product>{

	public List<Product> findByCategoryId(long categoryId);
	
	public List<Product> searchByName(String expression);

	public List<Product> searchByMinMax(int min, int max);

	public boolean deleteByCategoryId(long categoryId);
}
