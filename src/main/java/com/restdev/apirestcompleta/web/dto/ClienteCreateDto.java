package com.restdev.apirestcompleta.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record ClienteCreateDto(
    long id,
    @NotBlank
    @Size(min = 5, max = 100)
    String nome,
    @Size(min = 11, max = 11)
 //   @CPF
    String cpf)
{
}
