package com.restdev.apirestcompleta.service;
import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.exception.CpfUnique;
import com.restdev.apirestcompleta.exception.EntityNotFoundException;
import com.restdev.apirestcompleta.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    @Transactional
    public Cliente salvar(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUnique(
                    String.format("CPF '%s' não pode ser cadastrado, já existe no sistema", cliente.getCpf())
            );
        }
    }
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema", id))
        );
    }
//
//    @Transactional(readOnly = true)
//    public Page<ClienteProjection> buscarTodos(Pageable pageable) {
//        return clienteRepository.findAllPageable(pageable);
//    }
//
//    @Transactional(readOnly = true)
//    public Cliente buscarPorUsuarioId(Long id) {
//        return clienteRepository.findByUsuarioId(id);
//    }
}
