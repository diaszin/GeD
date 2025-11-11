package com.projetofinal.ged.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pasta")
@Getter
@Setter
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
