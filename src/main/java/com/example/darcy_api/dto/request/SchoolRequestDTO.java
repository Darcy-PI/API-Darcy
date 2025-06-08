package com.example.darcy_api.dto.request;

import com.example.darcy_api.enums.TipoEscola;
import com.example.darcy_api.enums.TurnoEnsino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRequestDTO {
    @NotBlank(message = "O nome da Escola não pode estar vazio")
    @Size(max = 80)
    private String nomeEscola;

    @NotBlank(message = "O endereco não pode estar vazio")
    @Size(max = 80)
    private String endereco;

    @NotBlank(message = "O numero da Cordenacao não pode estar vazio")
    @Size(max = 12)
    private String numeroCordenacao;

    @NotBlank(message = "O Turno do Ensino não pode estar vazio")
    private TurnoEnsino turnoEnsino;

    @NotBlank(message = "O Tipo da Escola não pode estar vazio")
    private TipoEscola tipoEscola;

    @NotBlank(message = "O Id do Pedagogo não pode estar vazio")
    private UUID pedagogueId;
}
