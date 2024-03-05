package com.cgi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cgi.dtos.UserDto;
import com.cgi.model.UserApp;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto userAppToUserDto(UserApp user);
	
	@Mapping(target = "password", ignore = true)
	UserApp userDtoToUserApp(UserDto userDto);
}
