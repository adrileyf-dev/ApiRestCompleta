package com.restdev.apirestcompleta.service;

import com.restdev.apirestcompleta.entity.User;
import com.restdev.apirestcompleta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User salvar(User usuario) {
        return  repository.save(usuario);
    }
}
