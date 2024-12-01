package com.restdev.apirestcompleta.Dto;

import com.restdev.apirestcompleta.Enum.Role;

public record UsuarioDto(String username, String password , Role role) {
}
