package com.restdev.apirestcompleta.web.controller;

import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.service.ClienteVagaService;
import com.restdev.apirestcompleta.service.EstacionamentoService;
import com.restdev.apirestcompleta.web.dto.EstacionamentoCreateDto;
import com.restdev.apirestcompleta.web.dto.EstacionamentoResponseDto;
import com.restdev.apirestcompleta.web.dto.mapper.ClienteVagaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.v1.base-path}/estacionamento")
public class EstacionamentoController {
    private final EstacionamentoService service;
   // private final ClienteVagaMapper2 mapper;
    private final ClienteVagaService clienteVagaService;

    @PostMapping("/check-in")
    public ResponseEntity<EstacionamentoResponseDto> checkIn(@RequestBody @Valid EstacionamentoCreateDto dto) {
        ClienteVaga clienteVaga = ClienteVagaMapper.toClienteVaga(dto);
        service.checkIn(clienteVaga);
        EstacionamentoResponseDto responseDto = ClienteVagaMapper.toDto(clienteVaga);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{recibo}")
                .buildAndExpand(clienteVaga.getRecibo())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("/teste")
    public String ver (){
        return  "asdasdas";
    }


    //@PreAuthorize("hasAnyRole('(ADMIN','CLIENTE')")
    @GetMapping("/check-in/{recibo}")
    public ResponseEntity<EstacionamentoResponseDto> getByRecibo(@PathVariable String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }

}
