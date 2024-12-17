package com.restdev.apirestcompleta.web.controller;

import com.restdev.apirestcompleta.entity.Vaga;
import com.restdev.apirestcompleta.service.VagaService;
import com.restdev.apirestcompleta.web.dto.DtoVaga;
import com.restdev.apirestcompleta.web.dto.mapper.VagaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.v1.base-path}/vagas")
public class VagaController {
    private final VagaService service;
    private final VagaMapper vagaMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody @Valid DtoVaga dtoVaga){
        Vaga vaga = vagaMapper.ToVaga(dtoVaga);
        service.salvar(vaga);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{codigo}")
                .buildAndExpand(vaga.getCodigo()).toUri();
       return  ResponseEntity.created(location).build();
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DtoVaga> getByCodigo(@PathVariable String codigo){
        Vaga vaga = service.buscarPorCodigo(codigo);
       return ResponseEntity.ok(vagaMapper.VagaDto(vaga));

   }
}
