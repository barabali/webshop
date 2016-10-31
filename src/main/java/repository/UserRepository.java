package repository;


import model.user.User;

public interface UserRepository extends Repository<User> {
	
	public User findByEmail(String email);
	
	public long getSpentMoney(long user_id);
	
}
