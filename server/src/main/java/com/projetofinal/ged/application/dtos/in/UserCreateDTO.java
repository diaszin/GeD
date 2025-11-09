package com.projetofinal.ged.application.dtos.in;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserCreateDTO {
    @NotBlank(message = "O nome não pode ser vazio")
    public String name;

    @NotBlank(message = "O e-mail não pode ser vazio")
    @Email(message = "E-mail inválido")
    public String email;

    @Size(min = 8, message = "A senha deve conter mais de 8 caracteres")
    public String password;

    @DateTimeFormat()
    @NotNull(message = "Data de nascimento não pode ser vazia")
    public Date birthdayDate;
}
