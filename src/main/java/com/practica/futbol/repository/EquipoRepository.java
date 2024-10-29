package com.practica.futbol.repository;

import com.practica.futbol.entities.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EquipoRepository extends MongoRepository<Equipo, String> {

}