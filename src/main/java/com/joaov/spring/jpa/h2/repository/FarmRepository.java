package com.joaov.spring.jpa.h2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.joaov.spring.jpa.h2.model.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
