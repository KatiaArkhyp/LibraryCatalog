package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.UserWithRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserWithRole, Long> {
    UserWithRole findByEmail(String email);
    UserWithRole findByUsername(String username);
    UserWithRole findFirstByUsername(String username);

    @Modifying
    @Query("UPDATE users u SET u.ban=:ban WHERE u.email=:email")
    void updateUserBan(@Param("ban") boolean ban, @Param("email") String email);
}
