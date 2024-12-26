package com.restdev.apirestcompleta.service;

import com.restdev.apirestcompleta.Util.EstacionamentoUtil;
import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.entity.Vaga;
import com.restdev.apirestcompleta.repository.ClienteVagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {
    private final ClienteVagaService clienteVagaService;
    private final ClienteService clienteService;
    private final VagaService vagaService;
    private final ClienteVagaRepository clienteVagaRepository;
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
    @Transactional
    public ClienteVaga chekOut(String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        LocalDateTime dataSaida = LocalDateTime.now();
        BigDecimal valor = EstacionamentoUtil.calcularCusto(clienteVaga.getDtEntrada(),dataSaida);
        clienteVaga.setValor(valor);
        long totalvezes = clienteVagaService.getTotalVeszesEstacionamento(clienteVaga.getCliente().getCpf());
        BigDecimal desconto=  EstacionamentoUtil.calcularDesconto(valor,totalvezes);
        clienteVaga.setDesconto(desconto);
        clienteVaga.setDtSaida(dataSaida);
        clienteVaga.getVaga().setStatusVaga(Vaga.StatusVaga.LIVRE);
        clienteVagaRepository.save(clienteVaga);
        return  clienteVaga;

    }
}
