package webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUserId(long userId);

}
