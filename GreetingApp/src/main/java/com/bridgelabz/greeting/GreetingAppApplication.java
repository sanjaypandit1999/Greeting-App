package com.bridgelabz.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GreetingAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GreetingAppApplication.class, args);
		System.out.println(context);
	}

}
