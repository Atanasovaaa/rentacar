package com.uni_projects.rentacar.repositories;

import com.uni_projects.rentacar.entities.Offer;
import com.uni_projects.rentacar.mappers.OfferRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;

@Repository
public class OfferRepository {

    private final JdbcTemplate db;

    public OfferRepository(JdbcTemplate db) {
        this.db = db;
    }

    public Offer fetchSingle(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_offers WHERE id = ")
                .append(id)
                .append(" AND is_existing = 1");

        ArrayList<Offer> offers = (ArrayList<Offer>) this.db.query(query.toString(), new OfferRowMapper());

        if(offers.isEmpty()) {
            return null;
        }

        return offers.get(0);
    }

    public Offer fetchByUserId(int userId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_offers WHERE user_id = ")
                .append(userId)
                .append(" AND is_existing = 1");

        ArrayList<Offer> offersCollection = (ArrayList<Offer>) this.db.query(query.toString(), new OfferRowMapper());

        if(offersCollection.isEmpty()) {
            return null;
        }

        return offersCollection.get(0);
    }

    public boolean create(Offer offer) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO td_offers(user_id, car_id, rent_days) ")
                .append("VALUES (")
                .append(offer.getUserId())
                .append(", ")
                .append(offer.getCarId())
                .append(", ")
                .append(offer.getRentDays())
                .append(")");

        this.db.execute(query.toString());
        return true;
    }

    public boolean activate(Offer offer) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_offers SET is_accepted = 1 ")
                .append("WHERE is_existing = 1 ")
                .append("AND id = ?");

        int resultCount = this.db.update(query.toString(), offer.getId());

        if(resultCount > 1) {
            throw new RuntimeException("More than one offer with same id exists");
        }

        return resultCount == 1;
    }

    public boolean updateTotalPrice(BigDecimal totalPrice, int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_offers SET total_price = ? ")
                .append("WHERE id = ? ")
                .append("AND is_existing = 1");


        int resultCount = this.db.update(query.toString(), totalPrice, id);

        if(resultCount > 1) {
            throw new RuntimeException("More than one offer with same id exists");
        }

        return resultCount == 1;
    }

    public boolean remove(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_offers ")
                .append("SET is_existing = 0 ")
                .append("WHERE id = ")
                .append(id);

        return this.db.update(query.toString()) > 0;
    }
}
