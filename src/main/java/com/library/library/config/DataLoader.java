package com.library.library.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.library.library.entity.User;
import com.library.library.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                repo.save(admin);
            }
            if (repo.findByUsername("student1").isEmpty()) {
                User stu = new User();
                stu.setUsername("student1");
                stu.setPassword(encoder.encode("student123"));
                stu.setRole("STUDENT");
                repo.save(stu);
            }
        };
    }
}
