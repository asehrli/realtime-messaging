package com.example.realtimemessaging.repository;

import com.example.realtimemessaging.entity.Role;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByName(String name);
}
