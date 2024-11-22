package com.uni_projects.rentacar.mappers;

import com.uni_projects.rentacar.entities.Offer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {

    @Override
    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Offer offer = new Offer();
        offer.setId(rs.getInt("id"));
        offer.setUserId(rs.getInt("user_id"));
        offer.setCarId(rs.getInt("car_id"));
        offer.setRentDays(rs.getInt("rent_days"));
        offer.setTotalPrice(rs.getBigDecimal("total_price"));

        return offer;
    }
}
