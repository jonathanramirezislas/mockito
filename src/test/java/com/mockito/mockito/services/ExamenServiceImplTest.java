package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;
import com.mockito.mockito.repositories.ExamenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //habilitamos la ijection de independecia sy mas
class ExamenServiceImplTest {

    @Mock
    ExamenRepository repository;



    @InjectMocks
    ExamenServiceImpl service;

    @Test
    void findExamenPorNombre(){


        when(repository.findAll()).thenReturn(Datos.EXAMENES);//cuando se invoque find all metodo repository retorna Datos.EXAMENES
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");

        assertTrue(examen.isPresent());
        assertEquals(1L, examen.orElseThrow().getId());
        assertEquals("Matematicas", examen.get().getNombre());

    }

}

/*

 */