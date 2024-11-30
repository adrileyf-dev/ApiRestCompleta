package com.restdev.apirestcompleta.Controller;

import com.restdev.apirestcompleta.entity.User;
import com.restdev.apirestcompleta.repository.UserRepository;
import com.restdev.apirestcompleta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService service;
    private final UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User usuario){
       User user = service.salvar(usuario);
         return  ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User>findById(@PathVariable Long id){
           User user = service.findById(id);
        return  ResponseEntity.ok(user);
    }
    @GetMapping("/all")
    public ResponseEntity <List<User>>findAll(){
       List<User> user = service.findAll();
        return  ResponseEntity.ok(user);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User>UpdatePassword(@PathVariable Long id,@RequestBody User user){
        User usuario = service.UpdatePassword(id,user.getPassword());
        return  ResponseEntity.ok(user);
    }
}
