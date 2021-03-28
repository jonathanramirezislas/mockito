package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;
import com.mockito.mockito.repositories.ExamenRepository;
import com.mockito.mockito.repositories.PreguntaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //habilitamos la ijection de independecia sy mas
class ExamenServiceImplTest {

    @Mock
    ExamenRepository repository;

    @Mock
    PreguntaRepository preguntaRepository;


    @InjectMocks
    ExamenServiceImpl service;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this); //otra forma de permitir injection de mocks
//       //siguiente codigo en caos de qu eno se quiera usar injectiones( Mocks, InjectionMocks)
//        repository = mock(ExamenRepository.class);
//        preguntaRepository = mock(PreguntaRepository.class);
//        service = new ExamenServiceImpl(repository, preguntaRepository);
    }


    @Test
    void findExamenPorNombre(){
        when(repository.findAll()).thenReturn(Datos.EXAMENES);//cuando se invoque find all metodo repository retorna Datos.EXAMENES
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");

        assertTrue(examen.isPresent());
        assertEquals(1L, examen.orElseThrow().getId());
        assertEquals("Matematicas", examen.get().getNombre());

    }

    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = Collections.emptyList();

        when(repository.findAll()).thenReturn(datos);//cuando se invoque findALL retorna una lista vacia
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");

        assertFalse(examen.isPresent());//como es vacia la lista no encuentra matematicas
    }

    @Test
    void testPreguntasExamen() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);//cuando se llame findAll retorna Examenes
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS); //cuando se llame findPreguntas... con cualquier ID retorna Preguntas otra forma retorna null
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas"); //Matematicas  ->INVOCARA findAll, findExamenConPreguntas
        assertEquals(5, examen.getPreguntas().size()); //hasta a hora son 5 preguntas en el examen de matematicas
        assertTrue(examen.getPreguntas().contains("integrales"));//almenos debe contener integrales

    }

}

/*

 */