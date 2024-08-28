package com.cgi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cgi.Exceptions.BusinessException;
import com.cgi.Exceptions.ErrorDto;
import com.cgi.dtos.UserDto;
import com.cgi.mappers.UserMapper;
import com.cgi.model.Role;
import com.cgi.model.Schedule;
import com.cgi.model.UserApp;
import com.cgi.repositories.UserRepository;
import com.cgi.repository.ScheduleRepository;

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
	
	@Autowired
	private  final ScheduleRepository scheduleRepo;
	
	

	public UserService(UserMapper userMapper, UserRepository userRepo,ScheduleRepository scheduleRepo) {
		this.userMapper = userMapper;
		this.userRepo = userRepo;
		this.scheduleRepo = scheduleRepo;
	}
    
    public UserDto getUserDTOFromUser(UserApp user) {
        return userMapper.userAppToUserDto(user);
    }
    
    public UserDto findByMailAndPassword(String mail, String password) {
    	UserApp user =  userRepo.findByMailAndPassword(mail, password);
    	if(user == null) {
    		ErrorDto error = new ErrorDto(401, HttpStatus.UNAUTHORIZED, "Email or Password invalid");
    		throw new BusinessException(error);
    	}   	
    	return getUserDTOFromUser(user);
    }
    
    public UserApp findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
    
    public List<UserApp> findEvaluators() {
        return userRepo.findByRole(Role.Evaluateur);
    }
    
    
    public List<UserApp> findAllUsers() {
        return userRepo.findAll();
    }
    
    
    public UserApp updateUser(Long id, UserApp userDetails) {
 
        UserApp existingUser = userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        existingUser.setNom(userDetails.getNom());
        existingUser.setPrenom(userDetails.getPrenom());
        existingUser.setMail(userDetails.getMail());
        existingUser.setPassword(userDetails.getPassword());
        UserApp updatedUser = userRepo.save(existingUser);
      
        return updatedUser;
    }
    
    public List<Schedule> getSchedulesByEvaluatorId(Long evaluatorId) {
        return scheduleRepo.findByEvaluatorId(evaluatorId);
    }
    
    
}
