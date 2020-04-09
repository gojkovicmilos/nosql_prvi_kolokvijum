package com.nosqlbaze.prvikolokvijum.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nosqlbaze.prvikolokvijum.model.User;
import com.nosqlbaze.prvikolokvijum.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
  
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {

      try {
        List<User> users = new ArrayList<User>();
    
        if (username == null)
          userRepository.findAll().forEach(users::add);
        else
          users.add(userRepository.findByUsername(username).get());
    
        if (users.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
        return new ResponseEntity<>(users, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }


    }
  
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {

      Optional<User> userData = userRepository.findById(id);

      if (userData.isPresent()) {
        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      
    }
  
  
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {

      Optional<User> userData = userRepository.findById(id);

      if (userData.isPresent()) {
        User _user = userData.get();
        _user.setUsername(user.getUsername());
        _user.setPassword(user.getPassword());
        _user.setEmail(user.getEmail());
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      
    }
  
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {

        try {
          userRepository.deleteById(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
      }
      
  
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
      
      
      try {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
    }
  
  
  }