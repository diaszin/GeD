package com.projetofinal.ged.infra;

import com.projetofinal.ged.adapters.BcryptPasswordCriptographyAdapter;
import com.projetofinal.ged.adapters.JPAUserRepositoryAdapter;
import com.projetofinal.ged.adapters.JWTAuthServiceAdapter;
import com.projetofinal.ged.adapters.UserServiceAdapter;
import com.projetofinal.ged.ports.AuthServicePort;
import com.projetofinal.ged.ports.PasswordCriptographyPort;
import com.projetofinal.ged.ports.UserRepositoryPort;
import com.projetofinal.ged.ports.UserServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    UserRepositoryPort userRepositoryPort(){
        return new JPAUserRepositoryAdapter();
    }

    @Bean
    PasswordCriptographyPort passwordCriptographyPort(){
        return new BcryptPasswordCriptographyAdapter();
    }

    @Bean
    AuthServicePort authServicePort(){
        return new JWTAuthServiceAdapter();
    }

    @Bean
    UserServicePort userServicePort(UserRepositoryPort repositoryPort, PasswordCriptographyPort passwordCriptographyPort, AuthServicePort authServicePort){
        return new UserServiceAdapter(repositoryPort,passwordCriptographyPort, authServicePort);
    }
}
