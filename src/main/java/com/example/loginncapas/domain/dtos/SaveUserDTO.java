package com.example.loginncapas.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveUserDTO {
    @NotEmpty
    @Pattern(regexp = "^[a-z]{4,16}$", message = "El campo debe contener entre 4 y 16 letras minúsculas")
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}
