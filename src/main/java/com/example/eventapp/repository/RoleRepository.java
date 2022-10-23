package com.example.eventapp.repository;

import com.example.eventapp.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    public Optional<Role> findRoleByName(String name);
}
