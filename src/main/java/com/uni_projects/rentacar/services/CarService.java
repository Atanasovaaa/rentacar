package com.uni_projects.rentacar.services;

import com.uni_projects.rentacar.entities.Car;
import com.uni_projects.rentacar.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean addCar(Car car) {
        return this.carRepository.add(car);
    }
}
