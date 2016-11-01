package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.user.User;
import repository.UserRepository;

public class DummyUserRepository implements UserRepository {
	
	private Map<String, User> users;
	
	public DummyUserRepository() {
		users = new HashMap<>();
		User testUser = new User("Test Name", "alreadyExists@test.com", "Test Address", "password");
		users.put(testUser.getEmail(), testUser);
	}

	@Override
	public void create(User user) {
		users.put(user.getEmail(), user);
	}

	@Override
	public void deleteById(long id) {
	}

	@Override
	public void update(User entity) {}

	@Override
	public User findById(long id) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return users.get(email);
	}

	@Override
	public long getSpentMoney(long user_id) {
		return 0;
	}

}
