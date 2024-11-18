package com.uni_projects.rentacar.repositories;

import com.uni_projects.rentacar.entities.Car;
import com.uni_projects.rentacar.mappers.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    private final JdbcTemplate db;

    public CarRepository(JdbcTemplate db) {
        this.db = db;
    }

    public boolean add(Car car) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO td_cars ")
                .append("(brand_name, model_name, city_location, price_per_day) ")
                .append("VALUES ")
                .append("('")
                .append(car.getBrandName())
                .append("', '")
                .append(car.getModelName())
                .append("', '")
                .append(car.getCityLocation())
                .append("', ")
                .append(car.getPricePerDay())
                .append(")");

        this.db.execute(query.toString());
        return true;
    }

//    public List<Car> fetchAll(String customerLocation) {
//        StringBuilder query = new StringBuilder();
//        query.append("SELECT * FROM td_cars WHERE city_location = '")
//                .append(customerLocation)
//                .append("'");
//
//        return this.db.query(query.toString(), new CarRowMapper());
//    }

    public List<Car> fetchAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_cars");

        return this.db.query(query.toString(), new CarRowMapper());
    }

    public Car fetchSingle(int carId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_cars WHERE id_car = '")
                .append(carId)
                .append("'");

        return this.db.queryForObject(query.toString(), new CarRowMapper());
    }
}
