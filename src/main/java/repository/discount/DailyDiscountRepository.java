package repository.discount;

import org.springframework.data.jpa.repository.JpaRepository;

import model.discount.DailyDiscount;

public interface DailyDiscountRepository extends JpaRepository<DailyDiscount, Long> {

}
