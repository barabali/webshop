package webshop.repository.discount;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.discount.TimedDiscount;

public interface TimedDiscountRepository extends JpaRepository<TimedDiscount, Long> {

}
