package com.ecommerce.repository;

import com.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    @EntityGraph(value = "loadSubCategories")
    Optional<Category> findById(Long id);

    @Override
    @EntityGraph(value = "loadSubCategories")
    List<Category> findAll();

    boolean existsByName(String name);
}
