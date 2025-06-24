package com.clinica.citasmedicas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.citasmedicas.model.Medico;
import com.clinica.citasmedicas.repository.MedicoRepository;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepo;

    @GetMapping("/lista")
    public List<Medico> listar() {
        return medicoRepo.findAll();
    }

    @PostMapping("/crear")
    public Medico crear(@RequestBody Medico medico) {
        return medicoRepo.save(medico);
    }
}