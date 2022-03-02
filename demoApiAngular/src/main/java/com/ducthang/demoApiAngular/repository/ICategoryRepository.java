package com.ducthang.demoApiAngular.repository;

import com.ducthang.demoApiAngular.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
