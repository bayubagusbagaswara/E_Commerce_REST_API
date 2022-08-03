package com.ecommerce.repository;

import com.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category>findAllByProductsId(String productId);
    Optional<Category> findAllByNameIgnoreCase(String name);
    List<Category> findAllByNameContainingIgnoreCase(String name);
    List<Category> findAllByNameIgnoreCaseStartsWith(String name);
}
