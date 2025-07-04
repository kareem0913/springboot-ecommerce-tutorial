package com.ecommerce.repository;

import com.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @EntityGraph(value = "loadUserRoles")
    Optional<User> findById(Long id);

    @Override
    @EntityGraph(value = "loadUserRoles")
    List<User> findAll();

    boolean existsByEmail(String name);

    @EntityGraph(value = "loadUserRoles")
    User findByEmail(String email);
}