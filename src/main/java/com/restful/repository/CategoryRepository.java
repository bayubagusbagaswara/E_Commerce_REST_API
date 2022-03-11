package com.restful.repository;

import com.restful.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category>findAllByProductsId(String productId);
    Optional<Category> findAllByNameIgnoreCase(String name);
    List<Category> findAllByNameContainingIgnoreCase(String name);
    List<Category> findAllByNameIgnoreCaseStartsWith(String name);
}
