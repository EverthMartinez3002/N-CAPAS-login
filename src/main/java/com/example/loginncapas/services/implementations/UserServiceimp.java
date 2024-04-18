package com.example.loginncapas.services.implementations;

import com.example.loginncapas.domain.dtos.SaveUserDTO;
import com.example.loginncapas.domain.entities.User;
import com.example.loginncapas.services.implementations.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceimp implements UserService {

    public static final List<User> users = new ArrayList<>(Arrays.asList(
            new User("capas", "capas123","capas@gmail.com")

    ));
        @Override
        public boolean register(SaveUserDTO info) {
            User userByUsername  = this.findByUsername(info.getUsername());
            User userByEmail = this.findByEmail(info.getEmail());

            if (userByUsername == null && userByEmail == null) {
                User newUser = new User();
                newUser.setUsername(info.getUsername());
                newUser.setPassword(info.getPassword());
                newUser.setEmail(info.getEmail());
                users.add(newUser);
                return true;
            }else  {
                return false;
            }

        }

    @Override
    public boolean login(SaveUserDTO info){
        User user = this.findByUsername(info.getUsername());
        User userEmail = this.findByEmail(info.getEmail());

        if (user != null || userEmail != null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<User> findAll(){
        return users;
    }

    @Override
    public User findByUsername(String username)  {
        return users.stream()
                .filter(b-> b.getUsername().equals(username))
                .findFirst()
                .orElse(null);

    }

    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(b-> b.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }


}
