package com.example.springboot;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

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

			System.out.println("Add ROLE_ADMIN role");
			if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
				Role r = new Role();
				r.setName("ROLE_ADMIN");
				roleRepository.save(r);
			}

			System.out.println("Add ROLE_USER role");
			if (roleRepository.findByName("ROLE_USER").isEmpty()) {
				Role r = new Role();
				r.setName("ROLE_USER");
				roleRepository.save(r);
			}

			System.out.println("Add a super user with all roles");
			Optional<User> adminOpt = userRepository.findByUsername("admin");
			User admin;

			if (adminOpt.isEmpty()) {
				admin = new User();
				admin.setUsername("admin");
				admin.setEmail("admin@example.com");
				admin.setPassword(passwordEncoder.encode("admin"));
				userRepository.save(admin);
			} else {
				admin = adminOpt.get();
			}

			Set<Role> roles = admin.getRoles();
			roleRepository.findAll().forEach(roles::add);
			userRepository.save(admin);

		};
	}

}
