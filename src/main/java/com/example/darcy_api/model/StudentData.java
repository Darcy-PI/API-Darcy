package com.example.darcy_api.model;

import com.example.darcy_api.enums.NecessidadeReforco;
import com.example.darcy_api.enums.TopicoDificuldade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @ManyToOne
    @JoinColumn(name = "id_ambiente_virtual")
    private VirtualClassroom virtualClassroom;

    @NotNull
    @Column(name = "grau_compreenssao")
    @Min(value = 1, message = "O grau de compreensão deve ser no mínimo 1")
    @Max(value = 5, message = "O grau de compreensão geral deve ser no máximo 5")
    private Integer grauCompreensao;

    @NotNull
    @Min(value = 1, message = "O grau de satisfação geral deve ser no mínimo 1")
    @Max(value = 5, message = "O grau de satisfação geral deve ser no máximo 5")
    @Column(name = "grau_interesse")
    private Integer grauInteresse;

    @NotNull
    @Column(name = "topico_dificuldade")
    private TopicoDificuldade topicoDificuldade;

    @NotNull
    @Min(value = 1, message = "O grau de autoconfiança deve ser no mínimo 1")
    @Max(value = 5, message = "O grau de autoconfiança deve ser no máximo 5")
    @Column(name = "grau_autoconfianca")
    private Integer grauAutoconfianca;

    @NotNull
    @Min(value = 1, message = "O grau de estado emocional deve ser no mínimo 1")
    @Max(value = 5, message = "O grau de estado emocional deve ser no máximo 5")
    @Column(name = "estado_emocional")
    private Integer estadoEmocional;

    @NotNull
    @Min(value = 1, message = "O grau de satisfação geral deve ser no mínimo 1")
    @Max(value = 5, message = "O grau de satisfação geral deve ser no máximo 5")
    @Column(name = "satisfacao_geral")
    private Integer satisfacaoGeral;

    @NotNull
    @Column(name = "necessidade_reforco")
    private NecessidadeReforco necessidadeReforco;

    @NotNull
    @Min(value = 0, message = "O tempo dedicado de estudo deve ser no mínimo 0")
    @Max(value = 12, message = "O tempo dedicado de estudo deve ser no máximo 12")
    @Column(name = "tempo_dedicado_estudo")
    private BigInteger tempoDedicadoEstudo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private Timestamp dataModificacao;

}