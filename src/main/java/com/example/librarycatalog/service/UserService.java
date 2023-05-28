package com.example.librarycatalog.service;

import com.example.librarycatalog.models.UserWithRole;

public interface UserService {
    void saveUser(UserWithRole user);

    UserWithRole findByEmail(String email);

    UserWithRole findByUsername(String nickname);
}
