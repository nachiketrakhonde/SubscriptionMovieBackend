package dev.movies.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository; // Your Mongo Repository

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Check if any admin already exists to avoid duplicates
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");

            // 2. IMPORTANT: Always encode the password
            admin.setPassword(passwordEncoder.encode("super_secret_password"));

            // 3. Assign the ADMIN role
            // Ensure your SecurityConfig uses .hasRole("ADMIN")
            // Spring prepends "ROLE_" automatically if you use .roles("ADMIN")
            admin.setRoles(List.of("ROLE_ADMIN", "ROLE_USER"));

            userRepository.save(admin);
            System.out.println("Initial Admin user created successfully.");
        }
    }
}
