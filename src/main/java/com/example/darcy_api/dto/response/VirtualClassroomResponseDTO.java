package com.example.darcy_api.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VirtualClassroomResponseDTO{

    private UUID id;

    private String nomeAmbiente;

    private String materia;

    private String serie;

    private String chaveAcesso;

    private Timestamp dataCriacao;

    private Timestamp dataUltimaModificacao;

    public Timestamp getDataUltimaModificacao() {
        return dataUltimaModificacao != null ? dataUltimaModificacao : dataCriacao;
    }
}
