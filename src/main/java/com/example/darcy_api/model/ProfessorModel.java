package com.example.darcy_api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_professor")
public class ProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private String nome_completo;

    @CreationTimestamp
    private Timestamp data_criarcao;

    @UpdateTimestamp
    private Timestamp data_modificacao;
}
