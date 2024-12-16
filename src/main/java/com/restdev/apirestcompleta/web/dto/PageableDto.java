package com.restdev.apirestcompleta.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record PageableDto(
        List<?> content,
        boolean first,
        boolean last,
        @JsonProperty("page") int number,
        int size,
        @JsonProperty("pageElements") int numberOfElements,
        int totalPages,
        int totalElementes
) {}
