package com.bridgelabz.greeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.greeting.dto.UserDto;
import com.bridgelabz.greeting.model.User;
import com.bridgelabz.greeting.repository.IGreetingRepository;
import com.bridgelabz.greeting.service.IGreetingService;

@RestController
@RequestMapping("/greet")
public class GreetingController {

	@Autowired
	private IGreetingService greetingService;

	@GetMapping(value = { "/", " " })
	public String getWelcomeMessage() {
		return greetingService.getHelloMessage();
	}

	@PostMapping("/customMessage")
	public String getCustomMessage(@RequestBody UserDto userDto) {
		return greetingService.getCustomMessage(userDto);
	}

	@GetMapping("/find_greeting/{id}")
	public User findGreetById(@PathVariable int id) {
		return greetingService.getGreetById(id);
	}

	@GetMapping("/get_all_greetings")
	public List<User> findAllGreeting() {
		return greetingService.getAllMessage();
	}

	@DeleteMapping("/delete_greet/{id}")
	public String deleteGreet(@PathVariable int id) {
		return greetingService.deleteGreet(id);
	}
}