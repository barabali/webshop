package webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.CombinedProduct;

public interface CombinedProductRepository extends JpaRepository<CombinedProduct, Long> {

}
