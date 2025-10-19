package com.projetofinal.ged.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserCreateDTO {
    public String name;
    public String email;
    public String password;

    @DateTimeFormat()
    public Date birthdayDate;
}
