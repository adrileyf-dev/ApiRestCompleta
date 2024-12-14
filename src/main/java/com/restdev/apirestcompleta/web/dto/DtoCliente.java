package com.restdev.apirestcompleta.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record DtoCliente(
        String nome,
        @NotBlank
        @Size(min = 11,max = 11)
       // @CPF
        String cpf
        ) {
}
