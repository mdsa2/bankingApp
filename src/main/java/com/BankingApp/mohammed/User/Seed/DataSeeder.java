package com.BankingApp.mohammed.User.Seed;

import com.BankingApp.mohammed.User.Entity.user;
import com.BankingApp.mohammed.User.Repositry.UserRepositry;
import com.BankingApp.mohammed.User.Enum.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepositry userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepositry userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            var adminUser = user.builder()
                    .firstName("mohammed")
                    .lastName("hasen")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.Admin)
                    .build();
            userRepository.save(adminUser);
        }
    }
}
