package model.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Order;

public class User {

	private Long id;
	private String name;
	private String email;
	private String address;
	private String password;

	private Set<Role> roles = new HashSet<>();
	private List<Order> orders;

	public User(String name, String email, String address, String password) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean hasRole(Role role) {
		return roles.contains(role);
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

}
