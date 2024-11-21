package com.uni_projects.rentacar.repositories;

import com.uni_projects.rentacar.entities.Car;
import com.uni_projects.rentacar.enums.CityNameType;
import com.uni_projects.rentacar.mappers.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private final JdbcTemplate db;

    public CarRepository(JdbcTemplate db) {
        this.db = db;
    }

    public boolean add(Car car) {
        if (!isValidCityName(car.getCityName())) {
            return false;
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO td_cars ")
                .append("(brand_name, model_name, city_name, price_per_day) ")
                .append("VALUES ")
                .append("('")
                .append(car.getBrandName())
                .append("', '")
                .append(car.getModelName())
                .append("', '")
                .append(car.getCityName())
                .append("', ")
                .append(car.getPricePerDay())
                .append(")");

        this.db.execute(query.toString());
        return true;
    }

    public List<Car> fetchAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_cars WHERE is_existing = 1");

        return this.db.query(query.toString(), new CarRowMapper());
    }

    public List<Car> fetchAllByUserCity(String city) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_cars WHERE city_name = '")
                .append(city)
                .append("' AND is_available = 1");

        return this.db.query(query.toString(), new CarRowMapper());
    }

    public Car fetchSingle(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_cars WHERE id = ")
                .append(id)
                .append(" AND is_existing = 1");

        ArrayList<Car> carsByCityCollection = (ArrayList<Car>) this.db.query(query.toString(), new CarRowMapper());

        if(carsByCityCollection.isEmpty()) {
            return null;
        }

        return carsByCityCollection.get(0);
    }

    public boolean update(Car car) {
        if (!isValidCityName(car.getCityName())) {
            return false;
        }

        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_cars ")
                .append("SET brand_name = ?, ")
                .append("model_name = ?, ")
                .append("city_name = ?, ")
                .append("price_per_day = ? ")
                .append("WHERE id = ?");

        int resultCount = this.db.update(query.toString(),
                car.getBrandName(),
                car.getModelName(),
                car.getCityName(),
                car.getPricePerDay(),
                car.getId());

        if (resultCount > 1) {
            throw new RuntimeException("Duplicate car id: " + car.getId());
        }

        return resultCount == 1;
    }

    private boolean isValidCityName(String cityName) {
        return cityName.equals(CityNameType.SOFIA.getCityNameType()) ||
                cityName.equals(CityNameType.PLOVDIV.getCityNameType()) ||
                cityName.equals(CityNameType.VARNA.getCityNameType()) ||
                cityName.equals(CityNameType.BURGAS.getCityNameType());
    }

    public boolean delete(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_cars ")
                .append("SET is_existing = 0 ")
                .append("WHERE id = ?");

        int resultCount = this.db.update(query.toString(), id);

        return resultCount == 1;
    }
}
