package com.example.darcy_api.dto.request;

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
public class PedagogueRequestDTO {

    @NotBlank(message = "O usuário não pode estar vazio")
    @Size(max = 15)
    private String usuario;

    @NotBlank(message = "O email não pode estar vazio")
    @Size(max = 30)
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    @Size(max = 30)
    private String senha;

    @NotBlank(message = "O nome completo não pode estar vazio")
    @Size(max = 80)
    private String nomeCompleto;
}
