package com.bridgelabz.greeting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.greeting.service.GreetingService;

@RestController
@RequestMapping("/greet")
public class GreetingController {
	@Autowired
	private GreetingService greetingService;
	
	@GetMapping(value={"/", " "})
	public String getWelcomeMessage() {
		return greetingService.getHelloMessage();
	}
}
