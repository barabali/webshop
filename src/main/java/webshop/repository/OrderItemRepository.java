package webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
