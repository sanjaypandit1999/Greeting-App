package com.bridgelabz.greeting.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService implements IGreetingService {
	
	public String getHelloMessage() {
		return "Hello I am Sanjay";
	}

}
