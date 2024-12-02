package com.restdev.apirestcompleta.Dto.mapper;

import com.restdev.apirestcompleta.Dto.UsuarioDto;
import com.restdev.apirestcompleta.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User ToUser(UsuarioDto usuarioDto);
    UsuarioDto ToDTo(User user);

    List<UsuarioDto> ToDToList(List<User> user);

}
