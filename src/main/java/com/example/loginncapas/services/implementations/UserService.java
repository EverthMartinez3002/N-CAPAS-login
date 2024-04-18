package com.example.loginncapas.services.implementations;

import com.example.loginncapas.domain.dtos.SaveUserDTO;
import com.example.loginncapas.domain.entities.User;

import java.util.List;


public interface UserService {
    boolean register(SaveUserDTO info);
    boolean login(SaveUserDTO info);
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
}
