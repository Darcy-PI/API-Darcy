package com.example.darcy_api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private boolean authenticated;

    private UUID id;

    private String role;

    private String usuario;
}
