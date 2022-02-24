package com.restful.repository;

import com.restful.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    // get category by name
    Optional<Category> findByNameContainingIgnoreCase(String name);

    List<Category> findByNameIgnoreCaseStartsWith(String name);
}
