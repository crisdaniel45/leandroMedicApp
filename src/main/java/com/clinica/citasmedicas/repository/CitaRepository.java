package com.clinica.citasmedicas.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.citasmedicas.model.Cita;
import com.clinica.citasmedicas.model.Medico;



public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoAndFecha(Medico medico, LocalDate fecha);
    boolean existsByMedicoAndFechaAndHora(Medico medico, LocalDate fecha, LocalTime hora);
}