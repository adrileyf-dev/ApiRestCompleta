package com.restdev.apirestcompleta.web.dto.mapper;

import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.web.dto.DtoCliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente ToCliente (DtoCliente dtoCliente);
    DtoCliente Todto  (Cliente cliente);

}
