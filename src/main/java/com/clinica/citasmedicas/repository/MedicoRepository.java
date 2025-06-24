package com.clinica.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.citasmedicas.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
