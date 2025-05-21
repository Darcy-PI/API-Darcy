package com.example.darcy_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(length = 15)
    private String usuario;

    @NotNull
    @Column(length = 30)
    private String senha;

    @NotNull
    @Column(name = "nome_completo", length = 80)
    private String nomeCompleto;

    @OneToMany(mappedBy = "professorOwner")
    private List<VirtualClassroom> ambientes;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private Timestamp dataModificacao;
}
