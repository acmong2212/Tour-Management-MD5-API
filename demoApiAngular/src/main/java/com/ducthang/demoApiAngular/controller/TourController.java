package com.ducthang.demoApiAngular.controller;

import com.ducthang.demoApiAngular.model.Tour;
import com.ducthang.demoApiAngular.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tour")
public class TourController {
    @Autowired
    ITourService tourService;

    @GetMapping
    public ResponseEntity<?> showTour() {
        return new ResponseEntity<>(tourService.findAllTour(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTour(@RequestBody Tour tour) {
        tourService.addTour(tour);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTourById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tourService.findTourById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTourById(@PathVariable("id") Long id, @RequestBody Tour tour) {
        tour.setId(id);
        tourService.addTour(tour);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        tourService.deleteTourById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchTour(@PathVariable("name") String name) {
        return new ResponseEntity<>(tourService.findAllByNameContaining(name), HttpStatus.OK);
    }
}