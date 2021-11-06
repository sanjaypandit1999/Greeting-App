package com.bridgelabz.greeting.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.greeting.dto.UserDto;
import com.bridgelabz.greeting.model.User;

@Service
public interface IGreetingService {
	public String getHelloMessage();
	public String getCustomMessage(UserDto userDto);
	public User getGreetById(int id);

}
