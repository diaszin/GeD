package com.projetofinal.ged.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name="user")
@Table(name = "usuarios")
public class JPAUserEntity {
    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "nome_completo", nullable = false)
    private String fullName;

    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date birthdayDate;
}
