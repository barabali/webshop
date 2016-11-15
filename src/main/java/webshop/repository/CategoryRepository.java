package webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
