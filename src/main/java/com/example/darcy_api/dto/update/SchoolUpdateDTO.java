package com.example.darcy_api.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolUpdateDTO{
    @NotBlank(message = "O nome da Escola não pode estar vazio")
    @Size(max = 80)
    private String nomeEscola;

    @NotBlank(message = "O endereco não pode estar vazio")
    @Size(max = 80)
    private String endereco;

    @NotBlank(message = "O numero da Cordenacao não pode estar vazio")
    @Size(max = 12)
    private String numeroCordenacao;

}