package webshop.repository.discount;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.discount.DailyDiscount;

public interface DailyDiscountRepository extends JpaRepository<DailyDiscount, Long> {

}
