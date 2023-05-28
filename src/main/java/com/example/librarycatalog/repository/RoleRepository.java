package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.Role;
import com.example.librarycatalog.models.UserWithRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
