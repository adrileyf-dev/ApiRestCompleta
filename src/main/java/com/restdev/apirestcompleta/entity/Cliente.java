package com.restdev.apirestcompleta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "cliente")
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String nome;
    @Column(nullable = false,unique = true,length = 11)
    private String cpf;
    @OneToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;
    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;
    @LastModifiedBy
    @Column(name = "modificado_por")
    private String modificadoPor;



}
