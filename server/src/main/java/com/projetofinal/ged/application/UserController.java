package com.projetofinal.ged.application;

import com.projetofinal.ged.application.responses.UserTokenResponse;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.application.dtos.UserCreateDTO;
import com.projetofinal.ged.application.dtos.UserLoginDTO;
import com.projetofinal.ged.infra.mappers.UserMapper;
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

    private final UserMapper userMapper = UserMapper.instance;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody() UserCreateDTO dto){
        this.userService.create(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> signIn(@RequestBody() UserLoginDTO dto){
        User currentUser = userMapper.loginDTOToDomainUser(dto);
        String token = this.userService.login(currentUser);

        return new ResponseEntity<>(new UserTokenResponse("Usuário encontrado", token), HttpStatus.OK);
    }
}
