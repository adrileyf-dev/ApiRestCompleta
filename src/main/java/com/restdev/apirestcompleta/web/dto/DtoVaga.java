package com.restdev.apirestcompleta.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DtoVaga(
        @NotBlank
        @Size(min = 4,max = 4)
        String codigo,
        @Pattern(regexp = "LIVRE|OCUPADA") String statusVaga
        ) {
}
