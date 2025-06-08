package com.example.darcy_api.dto.request;

import com.example.darcy_api.enums.NecessidadeReforco;
import com.example.darcy_api.enums.TopicoDificuldade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDataRequestDTO {

    @NotNull
    @NotBlank(message = "O id do estudante não pode estar vazio")
    private UUID studentId;

    @NotNull
    @NotBlank(message = "O id do ambiente virtual não pode estar vazio")
    private UUID virtualClassroomId;

    @NotNull
    @Size(min = 0, max = 5)
    @NotBlank(message = "O grau de compreensão não pode estar vazio")
    private Integer grauCompreensao;

    @NotNull
    @Size(min = 0, max = 5)
    @NotBlank(message = "O grau de interesse não pode estar vazio")
    private Integer grauInteresse;

    @NotNull
    @NotBlank(message = "O topico de dificuldade não pode estar vazio")
    private TopicoDificuldade topicoDificuldade;

    @NotNull
    @Size(min = 0, max = 5)
    @NotBlank(message = "O grau de autoconfiança não pode estar vazio")
    private Integer grauAutoconfianca;

    @NotNull
    @Size(min = 0, max = 5)
    @NotBlank(message = "O estado emocional não pode estar vazio")
    private Integer estadoEmocional;

    @NotNull
    @Size(min = 0, max = 5)
    @NotBlank(message = "A satisfação geral não pode estar vazio")
    private Integer satisfacaoGeral;

    @NotNull
    @NotBlank(message = "A opção necessidade de reforço não pode estar vazio")
    private NecessidadeReforco necessidadeReforco;

    @NotNull
    @Size(min = 0, max = 12)
    @NotBlank(message = "O tempo de estudo não pode estar vazio")
    private BigInteger tempoDedicadoEstudo;
}