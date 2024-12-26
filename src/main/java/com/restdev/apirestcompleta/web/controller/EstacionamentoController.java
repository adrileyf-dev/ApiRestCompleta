package com.restdev.apirestcompleta.web.controller;

import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.jwt.JwtUserDetails;
import com.restdev.apirestcompleta.repository.Projection.ClienteVagaProjection;
import com.restdev.apirestcompleta.service.ClienteVagaService;
import com.restdev.apirestcompleta.service.EstacionamentoService;
import com.restdev.apirestcompleta.web.dto.EstacionamentoCreateDto;
import com.restdev.apirestcompleta.web.dto.EstacionamentoResponseDto;
import com.restdev.apirestcompleta.web.dto.PageableDto;
import com.restdev.apirestcompleta.web.dto.mapper.ClienteVagaMapper;
import com.restdev.apirestcompleta.web.dto.mapper.PageMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.v1.base-path}/estacionamento")
public class EstacionamentoController {
    private final EstacionamentoService estacionamentoService;
    private final ClienteVagaService clienteVagaService;
    private final PageMapper pageMapper;


    @PostMapping("/check-in")
    public ResponseEntity<EstacionamentoResponseDto> checkIn(@RequestBody @Valid EstacionamentoCreateDto dto) {
        ClienteVaga clienteVaga = ClienteVagaMapper.toClienteVaga(dto);
        estacionamentoService.checkIn(clienteVaga);
        EstacionamentoResponseDto responseDto = ClienteVagaMapper.toDto(clienteVaga);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{recibo}")
                .buildAndExpand(clienteVaga.getRecibo())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }
    @GetMapping("/teste")
    public String ver() {
        return "asdasdas";
    }
    //@PreAuthorize("hasAnyRole('(ADMIN','CLIENTE')")
    @GetMapping("/check-in/{recibo}")
    public ResponseEntity<EstacionamentoResponseDto> getByRecibo(@PathVariable String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
}
  @PutMapping("/check-out/{recibo}")
    public ResponseEntity<EstacionamentoResponseDto> chekOut(@PathVariable String recibo) {
        ClienteVaga clienteVaga =estacionamentoService.chekOut(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
  }
  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<PageableDto>getAllEstacionamentoCpf(@PathVariable String cpf,
                                                              @PageableDefault(size=5, sort="dtEntrada",
                                                             direction =Sort.Direction.ASC) Pageable pageable){
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorCpf(cpf,pageable);
        PageableDto dto = pageMapper.ToDto(projection);
        return  ResponseEntity.ok(dto);
  }
  @GetMapping("/all")
  public ResponseEntity<PageableDto>getAllEstacionamentoCliente(@AuthenticationPrincipal JwtUserDetails user,
                                                                @PageableDefault(size=5, sort="dtEntrada",
                                                                direction =Sort.Direction.ASC) Pageable pageable){
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosUsuarioId(user.getId(),pageable);
        PageableDto dto = pageMapper.ToDto(projection);
        return  ResponseEntity.ok(dto);
  }
}
