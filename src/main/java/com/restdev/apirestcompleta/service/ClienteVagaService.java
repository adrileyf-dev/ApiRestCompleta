package com.restdev.apirestcompleta.service;


import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.exception.EntityNotFoundException;
import com.restdev.apirestcompleta.repository.ClienteRepository;
import com.restdev.apirestcompleta.repository.ClienteVagaRepository;
import com.restdev.apirestcompleta.repository.Projection.ClienteVagaProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteVagaService {
    private final ClienteVagaRepository repository;
    @Transactional
    public ClienteVaga Salvar(ClienteVaga clienteVaga){
        return  repository.save(clienteVaga);
    }
    public ClienteVaga buscarPorRecibo(String recibo) {
        return  repository.findClienteByReciboAndDtSaidaIsNull(recibo).orElseThrow(()->new EntityNotFoundException(String.format("Recibo '%s'não localizado ou chek-out realizaado!",recibo)));
    }

    @Transactional(readOnly = true)
    public long getTotalVeszesEstacionamento(String cpf) {
        return repository.countByClienteCpfAndDtSaidaIsNotNull(cpf);
    }

    @Transactional(readOnly = true)
    public Page<ClienteVagaProjection> buscarTodosPorCpf(String cpf, Pageable pageable) {
      return repository.findAllByClienteCpf(cpf,pageable);

    }
    @Transactional(readOnly = true)
    public Page<ClienteVagaProjection> buscarTodosUsuarioId(Long id, Pageable pageable) {
        return repository.findByClienteUsuarioId(id,pageable);
    }
}
