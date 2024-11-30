package com.restdev.apirestcompleta.repository;

import com.restdev.apirestcompleta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}