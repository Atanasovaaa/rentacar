package com.uni_projects.rentacar.controllers;

import com.uni_projects.rentacar.entities.Car;
import com.uni_projects.rentacar.http.AppResponse;
import com.uni_projects.rentacar.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

//    @GetMapping("/cars")
//    public ResponseEntity<?> fetchAllCars() {
//    }
//
//    @GetMapping("/cars/{carId}")
//    public ResponseEntity<?> fetchSingleCar(@PathVariable int carId) {
//
//    }

    @PostMapping("/cars")
    public ResponseEntity<?> addNewCar(@RequestBody Car car) {
        HashMap<String, Object> response = new HashMap<>();

        if(this.carService.addCar(car)) {
            return AppResponse.success()
                    .withMessage("Customer created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Customer could not be created")
                .build();
    }

//    @PutMapping("/cars")
//    public ResponseEntity<?> updateCar(@RequestBody Car car) {
//
//    }
//
//    @DeleteMapping("/cars/{id}")
//    public ResponseEntity<?> deleteCar(@PathVariable int id) {
//
//    }
}
