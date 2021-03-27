package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;
import com.mockito.mockito.repositories.ExamenRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements  ExamenService {
    private ExamenRepository examenRepository;

    public ExamenServiceImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Examen findExamenPorNombre(String nombre) {
         Optional<Examen> examenOptional = examenRepository.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();
         Examen examen= null;
         if(examenOptional.isPresent()){
             examen = examenOptional.orElseThrow();//si es null lanza una excepcion
         }
         return examen;

    }



}
