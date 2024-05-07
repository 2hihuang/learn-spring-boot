package com.example.springboot;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.RoleRepository;
import com.example.springboot.repository.UserRepository;

@SpringBootApplication
public class Application {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Sprint Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			// for (String beanName : beanNames) {
			// System.out.println(beanName);
			// }

			System.out.println("Add an init ADMIN role");
			if (roleRepository.findRoleByName("ADMIN").isEmpty()) {
				Role r = new Role();
				r.setName("ADMIN");
				roleRepository.save(r);
			}

			System.out.println("Add an init ADMIN user");

			if (userRepository.findUserByName("admin").isEmpty()) {
				User u = new User();
				u.setName("admin");
				u.setEmail("admin@example.com");
				u.setPassword(passwordEncoder.encode("admin"));
				u.setRoles(Collections.singleton(roleRepository.findRoleByName("ADMIN").get()));
				userRepository.save(u);
			}
		};
	}

}
