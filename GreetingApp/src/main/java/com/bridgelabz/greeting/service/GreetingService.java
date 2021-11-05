package com.bridgelabz.greeting.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bridgelabz.greeting.dto.UserDto;
import com.bridgelabz.greeting.model.User;

@Service
public class GreetingService implements IGreetingService {
	
	public String getHelloMessage() {
		return "Hello I am Sanjay";
	}
	public String getCustomMessage(UserDto userDto) {
	     User user = new User();
	     ModelMapper modelMapper = new ModelMapper();
	     modelMapper.map(userDto, user);
	     return ("Hello " + user.getFirstName() + " " + user.getLastName());
	 }
	

}
