package dev.movies.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabled for simple testing; enable & configure for production
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/movies/**").permitAll() // Anyone can browse
                        .requestMatchers(HttpMethod.POST, "/api/movies").hasRole("ADMIN") // Only Admins add movies
                        .requestMatchers(HttpMethod.POST, "/api/review/**").hasAnyRole("USER", "ADMIN") // Any logged-in user can review
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Uses basic auth for testing (Postman/Insomnia)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}