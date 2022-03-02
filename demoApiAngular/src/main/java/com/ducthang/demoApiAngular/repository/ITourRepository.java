package com.ducthang.demoApiAngular.repository;

import com.ducthang.demoApiAngular.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByNameContaining(String name);
}
