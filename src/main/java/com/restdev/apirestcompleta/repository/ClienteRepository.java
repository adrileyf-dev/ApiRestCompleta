package com.restdev.apirestcompleta.repository;

import com.restdev.apirestcompleta.entity.Cliente;
import com.restdev.apirestcompleta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}