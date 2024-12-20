package com.restdev.apirestcompleta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cliente")
@Getter @Setter @Service @AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cliente extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    @Column(nullable = false,unique = true,length = 11)
    private String cpf;
    @OneToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;


}
