package com.uni_projects.rentacar.services;

import com.uni_projects.rentacar.entities.User;
import com.uni_projects.rentacar.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int id) {
        return this.userRepository.fetchSingle(id);
    }
}
