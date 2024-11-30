package com.restdev.apirestcompleta.service;
import com.restdev.apirestcompleta.entity.User;
import com.restdev.apirestcompleta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    @Transactional
    public User salvar(User usuario) {
        return  repository.save(usuario);
    }
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return  repository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não Localizado"));
    }
    @Transactional
    public User UpdatePassword(Long id, String password) {
        User user = findById(id);
        user.setPassword(password);
        return  user;
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();

    }
}
