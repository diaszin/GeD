package com.projetofinal.ged.infra.entities;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pasta")
@Getter
@Setter
@DynamicUpdate
public class JPAFolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "titulo")
    String title;

    @Column(name = "data_de_criacao")
    @CreationTimestamp
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public JPAUserEntity createdBy;


    @Column(name = "data_de_atualizacao")
    @UpdateTimestamp
    Date updatedAt;

    @Transient
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<JPAFileEntity> files;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public JPAProjectEntity project;
}
