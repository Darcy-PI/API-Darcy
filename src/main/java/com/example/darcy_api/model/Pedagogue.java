package com.example.darcy_api.model;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pedagogue")
public class Pedagogue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(length = 15)
    private String usuario;

    @NotNull
    @Column(length = 80)
    private String senha;

    @NotNull
    @Column(length = 80)
    private String nomeCompleto;

    @Column(length = 30)
    private String email;

    @Column
    @CreationTimestamp
    private Timestamp dataCriacao;

    @Column
    @UpdateTimestamp
    private Timestamp dataUltimaModificacao;

}
