package com.example.darcy_api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    @NotBlank(message = "usuário não pode estar vazio")
    @Size(max = 15)
    private String usuario;

    @NotBlank(message = "usuário não pode estar vazio")
    @Size(max = 80)
    private String senha;
}
