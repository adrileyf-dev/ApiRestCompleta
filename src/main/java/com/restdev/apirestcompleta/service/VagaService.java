package com.restdev.apirestcompleta.service;

import com.restdev.apirestcompleta.entity.Vaga;
import com.restdev.apirestcompleta.exception.CodigoUniqueViolationExcept;
import com.restdev.apirestcompleta.exception.EntityNotFoundException;
import com.restdev.apirestcompleta.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VagaService {
    private final VagaRepository repository;

    @Transactional
    public Vaga salvar(Vaga vaga){
        try {
           return repository.save(vaga);
        }catch (DataIntegrityViolationException ex){
            throw new CodigoUniqueViolationExcept(String.format("Vaga ja cadastrada '%s'",vaga.getCodigo()));
        }
    }
    @Transactional(readOnly = true)
    public  Vaga buscarPorCodigo(String codigo){
        return repository.findByCodigo(codigo).orElseThrow(()-> new EntityNotFoundException(String.format("VAga não encontada")));
    }
}
