package com.projetofinal.ged.infra.mappers;

import com.projetofinal.ged.application.dtos.in.UserCreateDTO;
import com.projetofinal.ged.application.dtos.in.UserLoginDTO;
import com.projetofinal.ged.application.dtos.out.UserReadDTO;
import com.projetofinal.ged.domain.User;
import com.projetofinal.ged.infra.entities.JPAUserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T00:09:09-0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 25 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> toDomainUser(List<JPAUserEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( entity.size() );
        for ( JPAUserEntity jPAUserEntity : entity ) {
            list.add( toDomainUser( jPAUserEntity ) );
        }

        return list;
    }

    @Override
    public JPAUserEntity toJPAEntity(User user) {
        if ( user == null ) {
            return null;
        }

        JPAUserEntity jPAUserEntity = new JPAUserEntity();

        jPAUserEntity.setFullName( user.getFullName() );
        jPAUserEntity.setEmail( user.getEmail() );
        jPAUserEntity.setPassword( user.getPassword() );
        jPAUserEntity.setBirthdayDate( user.getBirthdayDate() );

        return jPAUserEntity;
    }

    @Override
    public User toDomainUser(JPAUserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String fullName = null;
        String email = null;
        String password = null;
        Date birthdayDate = null;

        fullName = entity.getFullName();
        email = entity.getEmail();
        password = entity.getPassword();
        birthdayDate = entity.getBirthdayDate();

        Long id = null;

        User user = new User( id, fullName, email, password, birthdayDate );

        return user;
    }

    @Override
    public User createDTOToDomainUser(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String fullName = null;
        String email = null;
        String password = null;
        Date birthdayDate = null;

        fullName = dto.name;
        email = dto.email;
        password = dto.password;
        birthdayDate = dto.birthdayDate;

        Long id = null;

        User user = new User( id, fullName, email, password, birthdayDate );

        return user;
    }

    @Override
    public User loginDTOToDomainUser(UserLoginDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = dto.email;
        password = dto.password;

        String fullName = null;
        Date birthdayDate = null;
        Long id = null;

        User user = new User( id, fullName, email, password, birthdayDate );

        return user;
    }

    @Override
    public UserReadDTO domainToReadDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String email = null;

        name = user.getFullName();
        email = user.getEmail();

        UserReadDTO userReadDTO = new UserReadDTO( name, email );

        return userReadDTO;
    }
}
