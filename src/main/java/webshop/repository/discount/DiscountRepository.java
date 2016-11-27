package webshop.repository.discount;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.discount.Discount;


public interface DiscountRepository  extends JpaRepository<Discount, Long> {

}
