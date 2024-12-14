package com.restdev.apirestcompleta.web.dto.mapper;

import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.web.dto.ClienteCreateDto;
import com.restdev.apirestcompleta.web.dto.ClienteResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {
    public static Cliente toCliente(ClienteCreateDto dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }
    public static ClienteCreateDto toDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteCreateDto.class);
    }
}
