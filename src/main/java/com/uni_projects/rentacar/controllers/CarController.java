package com.uni_projects.rentacar.controllers;

import com.uni_projects.rentacar.entities.Car;
import com.uni_projects.rentacar.http.AppResponse;
import com.uni_projects.rentacar.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/byCity/{userCity}")
    public ResponseEntity<?> fetchAllCarsByUserCity(@PathVariable String userCity) {
        ArrayList<Car> carCollection = (ArrayList<Car>) this.carService.getAllCarsByUserCity(userCity);

        if(!carCollection.isEmpty()) {
            return AppResponse.success()
                    .withData(carCollection)
                    .build();
        }

        return AppResponse.error()
                .withMessage("No cars found in our system")
                .build();
    }

    @GetMapping("/cars")
    public ResponseEntity<?> fetchAllCars() {
        ArrayList<Car> carCollection = (ArrayList<Car>) this.carService.getAllCars();

        if(!carCollection.isEmpty()) {
            return AppResponse.success()
                    .withData(carCollection)
                    .build();
        }

        return AppResponse.error()
                .withMessage("No cars found in our system")
                .build();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> fetchSingleCar(@PathVariable int id) {
        Car carResponse = this.carService.getSingleCarById(id);

        if (carResponse != null) {
            return AppResponse.success()
                    .withDataAsArray(carResponse)
                    .build();
        }

        return AppResponse.error()
                .withMessage("Car not found")
                .build();
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addNewCar(@RequestBody Car car) {

        if(this.carService.addCar(car)) {
            return AppResponse.success()
                    .withMessage("Car created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Car could not be created. Our available cities are Plovdiv, Sofia, Varna and Burgas.")
                .build();
    }

    @PutMapping("/cars")
    public ResponseEntity<?> updateCar(@RequestBody Car car) {
        boolean isUpdateSuccessful = this.carService.updateCar(car);

        if (isUpdateSuccessful) {
            return AppResponse.success()
                    .withMessage("Car data successfully updated")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Car could not be updated. Our available cities are Plovdiv, Sofia, Varna and Burgas")
                .build();
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {
        boolean isDeleteSuccessful = this.carService.deleteCar(id);

        if (isDeleteSuccessful) {
            return AppResponse.success()
                    .withMessage("Car has been removed")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Car could not be deleted")
                .build();
    }
}
