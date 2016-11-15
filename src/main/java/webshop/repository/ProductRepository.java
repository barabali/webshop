package webshop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategoryId(long categoryId);

	public List<Product> findByName(String name);
	
	public List<Product> findByBasePriceLessThan(BigDecimal max);
	
	public List<Product> findByBasePriceGreaterThan(BigDecimal min);
	
	public List<Product> findByBasePriceBetween(BigDecimal min, BigDecimal max);

	public void deleteByCategoryId(long categoryId);
}
