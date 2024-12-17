package com.restdev.apirestcompleta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {
    @CreatedDate
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;
    @LastModifiedDate
    @Column(name = "data_modificacao", nullable = false)
    private LocalDateTime dataModificacao;
    @CreatedBy
    @Column(name = "criado_por", updatable = false, nullable = false)
    private String criadoPor;
    @LastModifiedBy
    @Column(name = "modificado_por", nullable = false)
    private String modificadoPor;

}
