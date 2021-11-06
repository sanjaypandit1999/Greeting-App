package com.bridgelabz.greeting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.greeting.dto.UserDto;
import com.bridgelabz.greeting.model.User;

@Service
public interface IGreetingService {
	public String getHelloMessage();
	public String getCustomMessage(UserDto userDto);
	public User getGreetById(int id);
	public List<User> getAllMessage();
	public String deleteGreet(int id);
}
