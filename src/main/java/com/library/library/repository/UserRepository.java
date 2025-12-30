package com.library.library.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.library.library.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
