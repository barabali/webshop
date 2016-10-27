package repository;

import java.util.List;

public interface Repository<T> {

	public void create(T entity);
	
	public void deleteById(long id);
	
	public void update(T entity);
	
	public T findById(long id);
	
	public List<T> findAll();
	
}
