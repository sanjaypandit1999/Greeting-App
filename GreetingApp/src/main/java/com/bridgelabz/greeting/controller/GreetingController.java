package com.bridgelabz.greeting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.greeting.dto.UserDto;
import com.bridgelabz.greeting.repository.IGreetingRepository;
import com.bridgelabz.greeting.service.IGreetingService;

@RestController
@RequestMapping("/greet")
public class GreetingController {
	
	@Autowired
	private IGreetingService greetingService;
	
	@GetMapping(value={"/", " "})
	public String getWelcomeMessage() {
		return greetingService.getHelloMessage();
	}
	 @GetMapping("/customMessage")
	 public String getCustomMessage(@RequestBody UserDto userDto) {
		return greetingService.getCustomMessage(userDto);
	}
}
