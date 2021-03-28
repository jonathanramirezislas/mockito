package com.mockito.mockito.repositories;

import com.mockito.mockito.models.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository{

    @Override
    public List<Examen> findAll() {
        return Arrays.asList(new Examen(1L,"Matematicas"),new Examen(2L,"Español"),
                new Examen(3L,"Fisica"));
    }

    @Override
    public Examen guardar(Examen examen) {
        return null;
    }

}
