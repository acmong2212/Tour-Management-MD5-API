package com.ducthang.demoApiAngular.service;

import com.ducthang.demoApiAngular.model.Tour;

import java.util.List;

public interface ITourService {
    List<Tour> findAllTour();
    void addTour(Tour tour);
    Tour findTourById(Long id);
    void deleteTourById(Long id);
    List<Tour> findAllByNameContaining(String name);
}
