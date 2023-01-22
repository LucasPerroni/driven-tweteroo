package com.tweetero.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.DTO.UserDTO;
import com.tweetero.api.models.UserTable;
import com.tweetero.api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDTO req) {
        UserTable user = repository.findByUsername(req.username());

        if (user != null) {
            return ResponseEntity.status(409).body("Username already in use");
        } else {
            repository.save(new UserTable(req));
            return ResponseEntity.status(200).build();
        }
    }

}
