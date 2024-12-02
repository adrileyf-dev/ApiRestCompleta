package com.restdev.apirestcompleta.Controller;

import com.restdev.apirestcompleta.Dto.UsuarioDto;
import com.restdev.apirestcompleta.Dto.UsuarioUpdateSenhaDto;
import com.restdev.apirestcompleta.Dto.mapper.UserMapper;
import com.restdev.apirestcompleta.entity.User;
import com.restdev.apirestcompleta.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService service;
    private final UserMapper usuarioMapper;


    @PostMapping()
    public ResponseEntity<UsuarioDto> create(@Valid @RequestBody UsuarioDto usuariodto){
       User user = service.salvar(usuarioMapper.ToUser(usuariodto));
       return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.ToDTo(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto>findById(@PathVariable Long id){
           User user = service.findById(id);
        return  ResponseEntity.ok(usuarioMapper.ToDTo(user));
    }

    @GetMapping("/all")
    public ResponseEntity <List<UsuarioDto>>findAll(){
       List<User> user = service.findAll();
        return  ResponseEntity.ok(usuarioMapper.ToDToList(user));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void>UpdatePassword( @PathVariable Long id, @Valid @RequestBody UsuarioUpdateSenhaDto usuarioUpdateSenhaDto){
        User usuario = service.UpdatePassword(id,usuarioUpdateSenhaDto.senhaAtual(),usuarioUpdateSenhaDto.novaSenha(),usuarioUpdateSenhaDto.confirmaSenha());
        return  ResponseEntity.noContent().build();
    }
}
