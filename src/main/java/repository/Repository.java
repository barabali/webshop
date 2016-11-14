package repository;

import java.util.List;

public interface Repository<T> {

	
	public void delete(long id); //delete
	
	public void save(T entity); //save
	
	public T findOne(long id); //findOne
	
	public List<T> findAll();
	
}
