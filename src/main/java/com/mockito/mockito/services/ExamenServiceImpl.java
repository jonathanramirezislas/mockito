package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;
import com.mockito.mockito.repositories.ExamenRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements  ExamenService {
    private final ExamenRepository examenRepository;

    public ExamenServiceImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return examenRepository.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();
    }

}
