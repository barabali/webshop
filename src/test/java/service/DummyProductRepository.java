package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exception.ProductNotFoundException;
import model.Category;
import model.Product;
import repository.ProductRepository;

public class DummyProductRepository implements ProductRepository{
	private Map<Long,Product> products;
	private Long databaseGeneratedId=(long) 0;
	
	public DummyProductRepository() {
		products=new HashMap<>();
		Category cat=new Category("testCat");
		cat.setId(databaseGeneratedId);
		Product first=new Product("test",new BigDecimal("2000"), cat);
		products.put(databaseGeneratedId++, first);
	}

	@Override
	public void create(Product entity) {
		entity.setId(databaseGeneratedId);
		products.put(databaseGeneratedId++,entity);		
	}

	@Override
	public void deleteById(long id) {
		products.remove(id);
	}

	@Override
	public void update(Product entity) {
		products.remove(entity).getId();
		products.put(entity.getId(), entity);
	}

	@Override
	public Product findById(long id) {
		if(products.containsValue(id)==false)
			throw new ProductNotFoundException();
		return products.get(id);
	}

	@Override
	public List<Product> findAll() {
		List<Product> result=new ArrayList<>();
		Iterator<Product> i=products.values().iterator();
		while(i.hasNext()){
			Product tmp=(Product)i.next();
				result.add(tmp);
		}
		return result;
	}

	@Override
	public List<Product> findByCategoryId(long categoryId) {
		List<Product> result=new ArrayList<>();
		Iterator<Product> i=products.values().iterator();
		while(i.hasNext()){
			Product tmp=(Product)i.next();
			if(tmp.getCategory().getId()==categoryId)
				result.add(tmp);
		}
		return result;
	}

	@Override
	public List<Product> searchByName(String expression) {
		return null;
	}

	@Override
	public List<Product> searchByMinMax(int min, int max) {
		return null;
	}

	@Override
	public boolean deleteByCategoryId(long categoryId) {
		return false;
	}
	
}
