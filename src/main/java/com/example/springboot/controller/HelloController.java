package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/api/greeting")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
