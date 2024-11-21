package com.uni_projects.rentacar.controllers;

import com.uni_projects.rentacar.entities.User;
import com.uni_projects.rentacar.http.AppResponse;
import com.uni_projects.rentacar.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> fetchSingleUser(@PathVariable int id) {
        User responseUser = this.userService.getUser(id);

        if(responseUser != null) {
            return AppResponse.success()
                    .withDataAsArray(responseUser)
                    .build();
        }

        return AppResponse.error()
                .withMessage("User not found")
                .build();
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user) {

        if(this.userService.addUser(user)) {
            return AppResponse.success()
                    .withMessage("User created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User could not be created")
                .build();
    }
}
