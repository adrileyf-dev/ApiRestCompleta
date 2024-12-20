package com.restdev.apirestcompleta.service;

import com.restdev.apirestcompleta.Util.EstacionamentoUtil;
import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.entity.Vaga;
import jakarta.persistence.PrePersist;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.restdev.apirestcompleta.entity.Vaga.StatusVaga.OCUPADA;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {
    private final ClienteVagaService clienteVagaService;
    private final ClienteService clienteService;
    private final VagaService vagaService;
//    @Transactional
//    public ClienteVaga checkin(ClienteVaga clienteVaga){
//          Cliente cliente = clienteService.buscarPorCpf(clienteVaga.getCliente().getCpf());
//          clienteVaga.setCliente(cliente);
//          Vaga vaga = vagaService.buscarVagaLivre();
//          vaga.setStatusVaga(OCUPADA);
//          clienteVaga.setVaga(vaga);
//          clienteVaga.setDtEntrada(LocalDateTime.now());
//          clienteVaga.setRecibo(EstacionamentoUtil.gerarReceibo());
//          return clienteVagaService.Salvar(clienteVaga);
//        }

    @Transactional
    public ClienteVaga checkIn(ClienteVaga clienteVaga) {
        Cliente cliente = clienteService.buscarPorCpf(clienteVaga.getCliente().getCpf());
        clienteVaga.setCliente(cliente);

        Vaga vaga = vagaService.buscarVagaLivre();
        vaga.setStatusVaga(Vaga.StatusVaga.OCUPADA);
        clienteVaga.setVaga(vaga);

        clienteVaga.setDtEntrada(LocalDateTime.now());

        clienteVaga.setRecibo(EstacionamentoUtil.gerarReceibo());

        return clienteVagaService.Salvar(clienteVaga);


    }
    }
