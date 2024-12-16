package com.restdev.apirestcompleta.web.dto.mapper;

import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.web.dto.DtoCliente;
import com.restdev.apirestcompleta.web.dto.PageableDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {
    PageableDto ToDto  (Page page);


}
