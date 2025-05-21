package com.example.darcy_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_virtual_classroom")
public class VirtualClassroom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professorOwner;

    @Column(name = "access_key", unique = true, length = 8)
    private String accessKey;

    @Column(length = 30)
    private String materia;

    @Column(length = 2)
    private String serie;

    @NotNull
    @Column(name = "nome_ambiente", length = 40)
    private String nomeAmbiente;

    // Mapeamento de relação
    @ManyToMany(mappedBy = "ambientes")
    private List<Student> alunos;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private Timestamp dataModificacao;
}
