package com.example.darcy_api.model;


import com.example.darcy_api.enums.TipoEscola;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.darcy_api.enums.TurnoEnsino;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_escola")
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
    @Column(name = "turno_ensino")
    private TurnoEnsino turnoEnsino;

    @NotNull
    @Column(name = "tipo_escola")
    private TipoEscola tipoEscola;

    @Column(length = 12)
    private String numeroCordenacao;

    @OneToOne
    @JoinColumn(name = "id_pedagogue")
    private Pedagogue pedagogue;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_modificacao")
    private Timestamp dataUltimaModificacao;
}