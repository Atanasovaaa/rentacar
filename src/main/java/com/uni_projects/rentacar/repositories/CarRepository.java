package com.uni_projects.rentacar.repositories;

import com.uni_projects.rentacar.entities.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {
    private final JdbcTemplate db;

    StringBuilder query = new StringBuilder();

    public CarRepository(JdbcTemplate db) {
        this.db = db;
    }

    public boolean add(Car car) {
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
}
