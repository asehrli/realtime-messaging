package com.example.realtimemessaging.repository;

import com.example.realtimemessaging.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email); // always lower case
}
