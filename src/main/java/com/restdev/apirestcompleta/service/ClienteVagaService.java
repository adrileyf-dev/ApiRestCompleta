package com.restdev.apirestcompleta.service;


import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.exception.EntityNotFoundException;
import com.restdev.apirestcompleta.repository.ClienteRepository;
import com.restdev.apirestcompleta.repository.ClienteVagaRepository;
import lombok.RequiredArgsConstructor;
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
}
