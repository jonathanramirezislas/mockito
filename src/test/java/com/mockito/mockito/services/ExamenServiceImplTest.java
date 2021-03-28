package com.mockito.mockito.services;

import com.mockito.mockito.models.Examen;
import com.mockito.mockito.repositories.ExamenRepository;
import com.mockito.mockito.repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
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

    @Test
    void testPreguntasExamenVerify() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("integrales"));
        //verify sirve para ver si los metodos fueron llamados
        verify(repository).findAll();
        verify(preguntaRepository).findPreguntasPorExamenId(anyLong());

    }

    @Test
    void testNoExisteExamenVerify() {
        when(repository.findAll()).thenReturn(Collections.emptyList());//regresa una lista vacia de examenes
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Collections.emptyList());

        //when
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");

        //then
        assertNull(examen); //examen debe de ser null
        verify(repository).findAll(); //almenos se debe de llamar
       // verify(preguntaRepository).findPreguntasPorExamenId(5L); //daria error ya que no se debe de invocar este motodo
    }

    @Test
    void testGuardarExamen() {
        //creacion de ibjeto examen con sus preguntas
        Examen newExamen = Datos.EXAMEN;
        newExamen.setPreguntas(Datos.PREGUNTAS);

        //cuando en repositorio se llame el metodo guardar retorna el examen simulando que se guardo
        when(repository.guardar(any(Examen.class))).then(new Answer<Examen>(){

            Long secuencia = 4L;

            @Override
            public Examen answer(InvocationOnMock invocation) {
                Examen examen = invocation.getArgument(0);//obtener examen pasado por parametros
                examen.setId(secuencia++);
                return examen;
            }
        });

        Examen examen = service.guardar(newExamen);

        assertNotNull(examen.getId());
        assertEquals(4L, examen.getId());
        assertEquals("Historia", examen.getNombre());

        //Verififcacion de llamado de metodos con cualquiero objeto examen y una lista
        verify(repository).guardar(any(Examen.class));
        verify(preguntaRepository).guardarVarias(anyList());
    }


}

