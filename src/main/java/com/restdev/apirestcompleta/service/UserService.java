package com.restdev.apirestcompleta.service;
import com.restdev.apirestcompleta.entity.User;
import com.restdev.apirestcompleta.exception.NewPasswordMismatchException;
import com.restdev.apirestcompleta.exception.entityNotFoudExcept;
import com.restdev.apirestcompleta.exception.UsernameUniqueViolationException;
import com.restdev.apirestcompleta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    @Transactional
    public User salvar(User usuario) {
        try {
           throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado",usuario.getUsername()));
        }
        catch (DataIntegrityViolationException ex){
            return  repository.save(usuario);
        }
    }
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return  repository.findById(id).orElseThrow(
                ()-> new entityNotFoudExcept(String.format("Usuário não Localizado",id)));
    }
    @Transactional
    public User UpdatePassword(Long id, String SenhaAtual, String novaSenha,String confimaSenha) {
        if(!novaSenha.equals(confimaSenha))
            throw new NewPasswordMismatchException("Nova senha não confere com confirmação de senha");
        User user = findById(id);
        if(!user.getPassword().equals(SenhaAtual)){
            throw  new NewPasswordMismatchException("Sua senha não confere.");
        }
        user.setPassword(novaSenha);
        return user;
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }
}
