package dev.movies.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String password) {
        // Check if user exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User newUser = new User();
        newUser.setUsername(username);
        // HASH THE PASSWORD before saving
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(List.of("ROLE_USER")); // Default role

        return userRepository.save(newUser);
    }
}