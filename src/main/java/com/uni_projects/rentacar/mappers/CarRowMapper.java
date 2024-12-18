package com.uni_projects.rentacar.mappers;

import com.uni_projects.rentacar.entities.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setBrandName(rs.getString("brand_name"));
        car.setModelName(rs.getString("model_name"));
        car.setCityName(rs.getString("city_name"));
        car.setPricePerDay(rs.getBigDecimal("price_per_day"));

        return car;
    }
}
