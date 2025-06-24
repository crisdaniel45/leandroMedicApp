package com.clinica.citasmedicas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.citasmedicas.model.Paciente;
import com.clinica.citasmedicas.repository.PacienteRepository;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    @GetMapping("/lista")
    public List<Paciente> listar() {
        return pacienteRepo.findAll();
    }

    @PostMapping("/crear")
    public Paciente crear(@RequestBody Paciente paciente) {
        return pacienteRepo.save(paciente);
    }

    @GetMapping("/buscar")
    public Paciente buscarPorDni(@RequestParam String dni) {
        return pacienteRepo.findByDni(dni).orElse(null);
    }
}