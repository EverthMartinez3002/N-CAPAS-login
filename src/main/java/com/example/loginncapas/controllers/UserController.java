package com.example.loginncapas.controllers;


import com.example.loginncapas.domain.dtos.SaveUserDTO;
import com.example.loginncapas.domain.entities.User;
import com.example.loginncapas.services.implementations.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin
@Slf4j
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(
                userService.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveRegister(@RequestBody @Valid SaveUserDTO info, BindingResult errros) {

        if(errros.hasErrors()) {
           for(FieldError error : errros.getFieldErrors()) {
               if(error.getField().equals("username") && error.getCode().equals("Pattern")) {
                   return new ResponseEntity<>(
                           error.getDefaultMessage(),
                           HttpStatus.BAD_REQUEST
                   );
               }
           }

           return new ResponseEntity<>(
                   "Bad Request",
                   HttpStatus.BAD_REQUEST
           );
        }

        boolean isRegistered = userService.register(info);

        if (isRegistered){
            return new ResponseEntity<>(
                    "User saved",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "El usuario ya existe",
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SaveUserDTO info){
        boolean userFound = userService.login(info);

        if (userFound){
            return new ResponseEntity<>(
                    "Login con exito",
                    HttpStatus.OK
            );
        }else {
            return new ResponseEntity<>(
                    "Usuario no encontrado",
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
