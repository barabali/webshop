package webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.CombinedOrderItem;

public interface CombinedOrderItemRepository extends JpaRepository<CombinedOrderItem, Long> {

}
