package com.example.darcy_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.darcy_api.enums.TipoEnsino;
import com.example.darcy_api.enums.TurnoEnsino;

import java.security.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_Escola")
public class School{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(length = 80)
    private String nomeEscola;

    @NotNull
    @Column(length = 80)
    private String endereco;

    @NotNull
    @Column(name = "tipo_ensino")
    private TipoEnsino tipoEnsino;

    @NotNull
    @Column(name = "turno_ensino")
    private TurnoEnsino turnoEnsino;

    @Column(length = 12)
    private String numeroCordenacao;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_modificacao")
    private Timestamp dataUltimaModificacao;
}