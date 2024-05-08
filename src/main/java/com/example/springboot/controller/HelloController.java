package com.example.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

	@GetMapping("/api/greeting")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/login")
	public ModelAndView showLoginForm() {

		Map<String, String> selectedEnvVars = new HashMap<>();
		String[] envKeys = { "POD_NAME", "POD_NAMESPACE", "POD_IP" };

		for (String key : envKeys) {
			String value = System.getenv().getOrDefault(key, "");
			selectedEnvVars.put(key, value);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("envVars", selectedEnvVars);
		return modelAndView;
	}

	@GetMapping("/hello")
	public ModelAndView helloPage() {

		Map<String, String> selectedEnvVars = new HashMap<>();
		String[] envKeys = { "POD_NAME", "POD_NAMESPACE", "POD_IP" };

		for (String key : envKeys) {
			String value = System.getenv().getOrDefault(key, "");
			selectedEnvVars.put(key, value);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("envVars", selectedEnvVars);
		return modelAndView;
	}

}
