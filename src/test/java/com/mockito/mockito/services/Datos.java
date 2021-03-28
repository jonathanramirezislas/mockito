package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;

import java.util.Arrays;
import java.util.List;

public class Datos {
//      exmenes
    public final static List<Examen> EXAMENES = Arrays.asList(
            new Examen(1L, "Matematicas"),
            new Examen(2L, "Español"),
            new Examen(3L, "Fisica"));

}
