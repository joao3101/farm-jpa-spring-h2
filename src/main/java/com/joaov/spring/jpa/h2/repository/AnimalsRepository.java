package com.joaov.spring.jpa.h2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaov.spring.jpa.h2.model.Animals;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {

}
