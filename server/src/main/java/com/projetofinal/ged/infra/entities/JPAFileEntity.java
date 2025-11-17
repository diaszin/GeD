package com.projetofinal.ged.infra.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
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
    public String generatedFilename;

    @CreationTimestamp
    @Column(name = "data_de_upload")
    public Date uploadDate;
}
