package com.example.darcy_api.model;

import com.example.darcy_api.enums.NecessidadeReforco;
import com.example.darcy_api.enums.TopicoDificuldade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_estudante_data")
public class StudentData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_estudante")
    private Student student;

    @OneToOne
    @JoinColumn(name = "id_ambiente_virtual")
    private VirtualClassroom virtualClassroom;

    @NotNull
    @Column(name = "grau_compreenssao")
    private int grauCompreensao;

    @NotNull
    @Column(name = "grau_interesse")
    private int grauInteresse;

    @NotNull
    @Column(name = "topico_dificuldade")
    private TopicoDificuldade topicoDificuldade;

    @NotNull
    @Column(name = "grau_autoconfianca")
    private int grauAutoconfianca;

    @NotNull
    @Column(name = "estado_emocional")
    private int estadoEmocional;

    @NotNull
    @Column(name = "satisfacao_geral")
    private int satisfacaoGeral;

    @NotNull
    @Column(name = "necessidade_reforco")
    private NecessidadeReforco necessidadeReforco;

    @NotNull
    @Column(name = "tempo_dedicado_estudo")
    private BigInteger tempoDedicadoEstudo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private Timestamp dataModificacao;

}
