package com.projetofinal.ged.infra.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Table(name = "arquivo")
public class JPAFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "titulo")
    public String title;

    @ManyToOne
    @JoinColumn(name = "pasta_id")
    public JPAFolderEntity folder;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public JPAUserEntity user;

    @Column(name = "nome_do_arquivo")
    String generatedFilename;

    @Column(name = "versao")
    @ColumnDefault("1")
    int version;
}
