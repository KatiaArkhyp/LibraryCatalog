package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Role;
import com.example.librarycatalog.models.UserWithRole;
import com.example.librarycatalog.repository.RoleRepository;
import com.example.librarycatalog.repository.UserRepository;
import com.example.librarycatalog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserWithRole user) {
        UserWithRole userWithRole = new UserWithRole();
        userWithRole.setUsername(user.getUsername());
        userWithRole.setEmail(user.getEmail());
        userWithRole.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER");
        userWithRole.setRoles(Arrays.asList(role));
        userRepository.save(userWithRole);
    }

    @Override
    public UserWithRole findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserWithRole findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
