package com.cgi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cgi.dtos.UserDto;
import com.cgi.model.Schedule;
import com.cgi.model.UserApp;
import com.cgi.services.UserService;
import com.cgi.utils.UserRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestBody UserRequest userRequest) {
        UserDto user = userService.findByMailAndPassword(userRequest.getMail(), userRequest.getPassword());
        return user;
    }

    @GetMapping("/evaluators")
    public ResponseEntity<List<UserApp>> getEvaluators() {
        List<UserApp> evaluators = userService.findEvaluators();
        return ResponseEntity.ok(evaluators);
    }
    
    
    @GetMapping
    public List<UserApp> getAllUsers() {
        return userService.findAllUsers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserApp> getUserById(@PathVariable Long id) {
        UserApp user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserApp> updateUser(@PathVariable Long id, @RequestBody UserApp userDetails) {
        UserApp updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    
    }
        
}
