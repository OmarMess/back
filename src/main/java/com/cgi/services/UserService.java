package com.cgi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dtos.UserDto;
import com.cgi.mappers.UserMapper;
import com.cgi.model.UserApp;
import com.cgi.repositories.UserRepository;

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;



//@NoArgsConstructor
//@AllArgsConstructor
@Service
public class UserService {
	

	@Autowired
	private  final UserMapper userMapper;
	
	@Autowired
	private  final UserRepository userRepo;

	public UserService(UserMapper userMapper, UserRepository userRepo) {
		this.userMapper = userMapper;
		this.userRepo = userRepo;
	}
    
    public UserDto getUserDTOFromUser(UserApp user) {
        return userMapper.userAppToUserDto(user);
    }
    
    public UserDto findByMailAndPassword(String mail, String password) {
    	UserApp user =  userRepo.findByMailAndPassword(mail, password);
    	return getUserDTOFromUser(user);
    }
	

}
