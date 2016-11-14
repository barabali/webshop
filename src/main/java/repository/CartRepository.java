package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
