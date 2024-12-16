package com.restdev.apirestcompleta.web.controller;

import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.jwt.JwtUserDetails;
import com.restdev.apirestcompleta.repository.Projection.ClienteProjection;
import com.restdev.apirestcompleta.service.ClienteService;
import com.restdev.apirestcompleta.service.UsuarioService;
import com.restdev.apirestcompleta.web.dto.DtoCliente;
import com.restdev.apirestcompleta.web.dto.PageableDto;
import com.restdev.apirestcompleta.web.dto.mapper.ClienteMapper;
import com.restdev.apirestcompleta.web.dto.mapper.PageMapper;
import com.restdev.apirestcompleta.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "contém todas as opereções relativas ao recurso de um cliente")
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.v1.base-path}/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final ClienteMapper mapper;
    private final PageMapper pageMapper;

    @Operation(summary = "Criar um novo cliente",
            description = "Recurso para criar um novo cliente vinculado a um usuário cadastrado. " +
                    "Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENTE'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = DtoCliente.class))),
                    @ApiResponse(responseCode = "409", description = "Cliente CPF já possui cadastro no sistema",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por falta de dados ou dados inválidos",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permito ao perfil de ADMIN",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    ///@PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<DtoCliente> create(@RequestBody @Valid DtoCliente dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails) {
       Cliente cliente = mapper.ToCliente(dto);
       cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
       clienteService.salvar(cliente);
       return ResponseEntity.status(201).body(mapper.Todto(cliente));
    }

    @Operation(summary = "Localizar um cliente", description = "Recurso para localizar um cliente pelo ID. " +
            "Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = DtoCliente.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permito ao perfil de CLIENTE",
                            content = @Content(mediaType = " application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DtoCliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(mapper.Todto(cliente));
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> getAll(Pageable  pageable) {
        Page<ClienteProjection> clientes = clienteService.buscarTodos(pageable);
        return ResponseEntity.ok(pageMapper.ToDto(clientes));
    }

    @Operation(summary = "Recuperar dados do cliente autenticado",
            description = "Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENTE'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = DtoCliente.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "Recurso não permito ao perfil de ADMIN",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @GetMapping("/detalhes")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<DtoCliente> getDetalhes(@AuthenticationPrincipal JwtUserDetails userDetails) {
        Cliente cliente = clienteService.buscarPorId(userDetails.getId());
        return ResponseEntity.ok(mapper.Todto(cliente));
    }
}
