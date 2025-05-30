package com.example.darcy_api.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "tb_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column (length = 15)
    private String usuario;

    @NotNull
    @Column(length = 30)
    private String senha;

    @NotNull
    @Column(name = "nome_completo", length = 80)
    private String nomeCompleto;

    // Criando tabela intermediária para relação N:N
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "aluno_ambiente",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_ambiente_virtual"))
    private List<VirtualClassroom> ambientes;

    @CreationTimestamp
    @JsonProperty("data_criacao")
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @JsonProperty("data_ultima_modificacao")
    @Column(name = "data_ultima_modificacao")
    private Timestamp dataUltimoModificacao;
}