package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.UserWithRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserWithRole, Long> {
    UserWithRole findByEmail(String email);
    UserWithRole findByUsername(String username);

    UserWithRole findFirstByUsername(String username);
}
