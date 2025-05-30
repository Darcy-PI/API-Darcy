package com.example.darcy_api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "professorDono")
    private List<VirtualClassroom> ambientes;

    @JsonProperty("data_criacao")
    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @JsonProperty("data_modificacao")
    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private Timestamp dataModificacao;
}
