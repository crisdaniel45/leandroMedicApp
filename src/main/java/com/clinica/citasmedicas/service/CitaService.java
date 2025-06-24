package com.clinica.citasmedicas.service;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.citasmedicas.model.Cita;
import com.clinica.citasmedicas.model.Medico;
import com.clinica.citasmedicas.model.Paciente;
import com.clinica.citasmedicas.repository.CitaRepository;
import com.clinica.citasmedicas.repository.MedicoRepository;
import com.clinica.citasmedicas.repository.PacienteRepository;


@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    public boolean citaDisponible(Medico medico, java.time.LocalDate fecha, java.time.LocalTime hora) {
        return !citaRepo.existsByMedicoAndFechaAndHora(medico, fecha, hora);
    }

    public Cita crearCita(Cita cita) {
        Medico medico = medicoRepo.findById(cita.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        Paciente paciente = pacienteRepo.findById(cita.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (!citaDisponible(medico, cita.getFecha(), cita.getHora())) {
            throw new RuntimeException("El horario ya está ocupado");
        }

        LocalTime inicio = medico.getHoraInicio();
        LocalTime fin = medico.getHoraFin();
        if (inicio == null || fin == null) {
            throw new RuntimeException("El médico no tiene horario definido");
        }

        if (cita.getHora().isBefore(inicio) || cita.getHora().isAfter(fin)) {
            throw new RuntimeException("Hora fuera del horario del médico");
        }

        cita.setMedico(medico);
        cita.setPaciente(paciente);

        return citaRepo.save(cita);
    }

    public List<Cita> obtenerCitas() {
        return citaRepo.findAll();
    }

    public void cancelarCita(Long id) {
        citaRepo.deleteById(id);
    }

    public Optional<Cita> actualizarCita(Long id, Cita nuevaCita) {
        return citaRepo.findById(id).map(cita -> {
            Medico medico = medicoRepo.findById(nuevaCita.getMedico().getId())
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
            Paciente paciente = pacienteRepo.findById(nuevaCita.getPaciente().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

            cita.setFecha(nuevaCita.getFecha());
            cita.setHora(nuevaCita.getHora());
            cita.setMedico(medico);
            cita.setPaciente(paciente);
            return citaRepo.save(cita);
        });
    }
}