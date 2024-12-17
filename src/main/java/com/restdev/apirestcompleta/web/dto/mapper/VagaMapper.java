package com.restdev.apirestcompleta.web.dto.mapper;

import com.restdev.apirestcompleta.entity.Vaga;
import com.restdev.apirestcompleta.web.dto.DtoVaga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VagaMapper {
       Vaga ToVaga  (DtoVaga dtoVaga);
    DtoVaga VagaDto (Vaga ToVaga);

}
