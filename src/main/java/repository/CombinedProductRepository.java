package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.CombinedProduct;

public interface CombinedProductRepository extends JpaRepository<CombinedProduct, Long> {

}
