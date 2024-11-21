package com.uni_projects.rentacar.services;

import com.uni_projects.rentacar.entities.Car;
import com.uni_projects.rentacar.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean addCar(Car car) {
        return this.carRepository.add(car);
    }

    public List<Car> getAllCars() {
        return this.carRepository.fetchAll();
    }

    public List<Car> getAllCarsByUserCity(String city){
        return this.carRepository.fetchAllByUserCity(city);
    }

    public Car getSingleCarById(int carId) {
        return this.carRepository.fetchSingle(carId);
    }

    public boolean updateCar(Car car) {
        return this.carRepository.update(car);
    }

    public boolean deleteCar(int carId) {
        return this.carRepository.delete(carId);
    }
}
