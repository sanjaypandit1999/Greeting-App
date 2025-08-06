package com.bridgelabz.greeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.greeting.dto.UserDto;
import com.bridgelabz.greeting.model.User;
import com.bridgelabz.greeting.repository.IGreetingRepository;
import com.bridgelabz.greeting.service.IGreetingService;
import com.bridgelabz.greeting.service.AuditLogService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/greet")
public class GreetingController {

	@Autowired
	private IGreetingService greetingService;
	
	@Autowired
	private AuditLogService auditLogService;

	@GetMapping(value = { "/", " " })
	public String getWelcomeMessage() {
		return greetingService.getHelloMessage();
	}

	@PostMapping("/customMessage")
	public String getCustomMessage(@RequestBody UserDto userDto, 
			@RequestParam(required = false, defaultValue = "unknown") String clientName,
			HttpServletRequest request) {
		String result = greetingService.getCustomMessage(userDto);
		auditLogService.logOperation(clientName, "CREATE_GREETING", 
			"Created greeting for: " + userDto.getFirstName() + " " + userDto.getLastName(), 
			getClientIpAddress(request));
		return result;
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
	public String deleteGreet(@PathVariable int id, 
			@RequestParam(required = false, defaultValue = "unknown") String clientName,
			HttpServletRequest request) {
		String result = greetingService.deleteGreet(id);
		auditLogService.logOperation(clientName, "DELETE_GREETING", 
			"Deleted greeting with ID: " + id, 
			getClientIpAddress(request));
		return result;
	}
	
	@PutMapping("/edit_message/{id}")
	public User editMesage(@PathVariable int id, @RequestBody UserDto userDto,
			@RequestParam(required = false, defaultValue = "unknown") String clientName,
			HttpServletRequest request) {
		User result = greetingService.updateMassage(id, userDto);
		auditLogService.logOperation(clientName, "UPDATE_GREETING", 
			"Updated greeting with ID: " + id + " for: " + userDto.getFirstName() + " " + userDto.getLastName(), 
			getClientIpAddress(request));
		return result;
	}
	
	private String getClientIpAddress(HttpServletRequest request) {
		String xForwardedForHeader = request.getHeader("X-Forwarded-For");
		if (xForwardedForHeader == null) {
			return request.getRemoteAddr();
		} else {
			return xForwardedForHeader.split(",")[0];
		}
	}
}