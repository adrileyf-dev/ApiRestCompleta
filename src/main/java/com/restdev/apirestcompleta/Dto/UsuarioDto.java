package com.restdev.apirestcompleta.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restdev.apirestcompleta.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UsuarioDto(
        @NotBlank
        @Email(message = "Formato de Email inválido!")
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank
        @Size(min = 6,max = 6)
        String password,
        LocalDateTime dtCreate,
        LocalDateTime dtUpdate,
        String createFor,
        String modifyFor,
        Role role
) {}