package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;

import java.util.Optional;

public interface ExamenService {
    Optional<Examen> findExamenPorNombre(String nombre);
}
