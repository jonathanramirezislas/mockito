package com.mockito.mockito.repositories;

import com.mockito.mockito.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
    Examen guardar(Examen examen);

}
