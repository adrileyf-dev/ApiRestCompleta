package com.restdev.apirestcompleta.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "ClienteVagas")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor


public class ClienteVaga extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  Long id;
    @Column(name = "numeroRecibo",unique = true,length = 15)
    private String recibo;
    @Column(length = 8,nullable = false)
    private String placa;
    @Column(length = 45,nullable = false)
    private String marca;
    @Column(length = 45,nullable = false)
    private String modelo;
    @Column(length = 45,nullable = false)
    private String cor;
    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal valor;
    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal desconto;
    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false )
    private  Cliente cliente;
    @Transient
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_vaga",nullable = false )
    private Vaga vaga;
    LocalDateTime dtEntrada;
    @PostPersist
    private void PostPersist(){
        dtEntrada = LocalDateTime.now();
    }
    LocalDateTime dtSaida;
}
