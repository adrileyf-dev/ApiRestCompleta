package com.restdev.apirestcompleta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "vagas")
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Vaga extends Auditable  implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "codigo", nullable = false,unique = true,length = 4)
    private String codigo;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private  StatusVaga statusVaga;
    public  enum StatusVaga{
       LIVRE,OCUPADA
    }
    @PrePersist
    private void prePersit(){
        statusVaga = StatusVaga.LIVRE;
    }
}
