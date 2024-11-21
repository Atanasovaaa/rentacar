package com.uni_projects.rentacar.repositories;

import com.uni_projects.rentacar.entities.User;
import com.uni_projects.rentacar.mappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {

    private final JdbcTemplate db;

    public UserRepository(JdbcTemplate db) {
        this.db = db;
    }

    public User fetchSingle(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_users WHERE is_active = 1 AND id = " + id);
        ArrayList<User> collection = (ArrayList<User>) this.db.query(query.toString(), new UserRowMapper());

        if(collection.isEmpty()) {
            return null;
        }

        return collection.get(0);
    }

    public boolean add(User user) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO td_users ")
                .append("(first_name, last_name, address, phone_number, age, has_crashes) ")
                .append("VALUES ")
                .append("('")
                .append(user.getFirstName())
                .append("', '")
                .append(user.getLastName())
                .append("', '")
                .append(user.getAddress())
                .append("', '")
                .append(user.getPhoneNumber())
                .append("', ")
                .append(user.getAge())
                .append(", ")
                .append(user.getHasCrashes())
                .append(")");

        this.db.execute(query.toString());
        return true;
    }
}
