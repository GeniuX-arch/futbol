package com.practica.futbol.controller;

import java.util.List;
import java.util.Map;

import com.practica.futbol.entities.Equipo;
import com.practica.futbol.repository.EquipoRepository;
import com.practica.futbol.tools.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping(value = "/api/equipos")
public class EquipoRestController {

    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping("/")
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipo getEquipoById(@PathVariable String id) {
        return equipoRepository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
    }

    @PostMapping("/")
    public Equipo saveEquipo(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Equipo equipo = mapper.convertValue(body, Equipo.class);
        return equipoRepository.save(equipo);
    }

    @PutMapping("/{id}")
    public Equipo updateEquipo(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Equipo equipo = mapper.convertValue(body, Equipo.class);
        equipo.setId(id);
        return equipoRepository.save(equipo);
    }

    @DeleteMapping("/{id}")
    public Equipo deleteEquipo(@PathVariable String id) {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
        equipoRepository.deleteById(id);
        return equipo;
    }
}
