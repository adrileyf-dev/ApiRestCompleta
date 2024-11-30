package com.restdev.apirestcompleta.Controller;

import com.restdev.apirestcompleta.entity.User;
import com.restdev.apirestcompleta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService service;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User usuario){
       User user = service.salvar(usuario);
         return  ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
