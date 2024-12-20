package com.restdev.apirestcompleta.repository;

import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.entity.ClienteVaga;
import com.restdev.apirestcompleta.repository.Projection.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteVagaRepository extends JpaRepository<ClienteVaga, Long> {


  Optional<ClienteVaga> findClienteByReciboAndDtSaidaIsNull (String recibo);
}