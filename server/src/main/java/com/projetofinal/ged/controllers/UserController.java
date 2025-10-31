package com.projetofinal.ged.controllers;

import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.dtos.UserCreateDTO;
import com.projetofinal.ged.dtos.UserLoginDTO;
import com.projetofinal.ged.ports.UserServicePort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserServicePort userService;

    @GetMapping
    public List<User> getAll(){
        return this.userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody() UserCreateDTO dto){
        this.userService.create(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> signIn(@RequestBody() UserLoginDTO dto){
        Boolean isAuthenticated = this.userService.login(dto);

        if(!isAuthenticated){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
