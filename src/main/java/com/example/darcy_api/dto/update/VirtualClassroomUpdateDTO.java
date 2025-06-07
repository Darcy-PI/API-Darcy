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
public class VirtualClassroomUpdateDTO {

    @NotBlank(message = "A matéria não pode estar vazio")
    @Size(max = 30)
    private String materia;

    @NotBlank(message = "A série não pode estar vazio")
    @Size(max = 3)
    private String serie;

    @NotBlank(message = "o nome do ambiente não pode estar vazio")
    @Size(max = 40)
    private String nomeAmbiente;
}
