package model.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.order.Order;

public class User {

	private Long id;
	private String name;
	private String email;
	private String address;
	private String password;

	private Set<Role> roles = new HashSet<>();
	private Map<Long,Order> orders = new HashMap<Long,Order>();

	public User(String name, String email, String address, String password) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.password = password;
	}
	
	public User(Long id,String name, String email, String address, String password) {
		this.id=id;
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

	public Map<Long,Order> getOrders() {
		return orders;
	}

	public void setOrders(Map<Long,Order> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}
	
	public Order findById(long order_id){
		return orders.get(order_id);
	}

}
