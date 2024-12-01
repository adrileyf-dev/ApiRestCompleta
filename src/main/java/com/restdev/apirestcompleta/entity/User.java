package com.restdev.apirestcompleta.entity;

import com.restdev.apirestcompleta.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    private String username;
    private String password;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String createFor;
    private String modifyFor;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role ;
    @PrePersist
    public void prePersist(){
        dtCreate = LocalDateTime.now();
    }
    @PreUpdate
    public void PreUpdate(){
        dtUpdate = LocalDateTime.now();
    }


}


