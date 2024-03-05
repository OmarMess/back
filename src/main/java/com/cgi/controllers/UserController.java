package com.cgi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.dtos.UserDto;
import com.cgi.model.UserApp;
import com.cgi.repositories.UserRepository;
import com.cgi.services.UserService;
import com.cgi.utils.UserRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserRequest userRequest) {
		UserDto user = userService.findByMailAndPassword(userRequest.getMail(), userRequest.getPassword());
		if(user != null)
			return ResponseEntity.ok(user);
		else  
			return ResponseEntity.notFound().build();
	}
	
	

}
