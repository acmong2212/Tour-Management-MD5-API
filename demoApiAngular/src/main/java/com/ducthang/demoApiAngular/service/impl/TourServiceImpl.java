package com.ducthang.demoApiAngular.service.impl;

import com.ducthang.demoApiAngular.model.Tour;
import com.ducthang.demoApiAngular.repository.ITourRepository;
import com.ducthang.demoApiAngular.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements ITourService {
    @Autowired
    ITourRepository tourRepository;

    @Override
    public List<Tour> findAllTour() {
        return tourRepository.findAll();
    }

    @Override
    public void addTour(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public Tour findTourById(Long id) {
        return tourRepository.findById(id).get();
    }

    @Override
    public void deleteTourById(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public List<Tour> findAllByNameContaining(String name) {
        return tourRepository.findAllByNameContaining(name);
    }


}
