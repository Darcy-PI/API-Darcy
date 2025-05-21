package com.example.darcy_api.model;

import java.security.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity (name = "TB_Student")
public class AlunoModel{
    private UUID id;

    // @OneToMany 
    // private UUID id_ambiente;

    @NotNull
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private String nome_completo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_modificacao")
    private Timestamp dataUltimoMapeamento;
}