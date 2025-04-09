package com.example.realtimemessaging.repository;

import com.example.realtimemessaging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // always lower case
}
