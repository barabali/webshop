package webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
