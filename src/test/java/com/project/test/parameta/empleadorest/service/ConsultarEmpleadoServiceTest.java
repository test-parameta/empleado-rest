package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadorest.repository.EmpleadoRepository;
import com.project.test.parameta.empleadorest.service.impl.ConsultarEmpleadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.MENSAJE_ERROR_CONSULTA_EMPLEADOS;
import static com.project.test.parameta.empleadorest.utils.constants.Constantes.REST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultarEmpleadoServiceTest {

    @InjectMocks private ConsultarEmpleadoService consultarEmpleadoService;
    @Mock @Qualifier(REST) private EmpleadoRepository empleadoRepository;
    @Spy private EmpleadoMapper empleadoMapper;

    @Test
    void consultarEmpleadosExitoso(){
        when(empleadoRepository.findAll()).thenReturn(new ArrayList<>());
        RespuestaGeneralDTO respuestaGeneralDTO = consultarEmpleadoService.consultarEmpleados();
        assertEquals(respuestaGeneralDTO.getStatus(), HttpStatus.OK);
    }

    @Test
    void consultarEmpleadosFallido(){
        when(empleadoRepository.findAll()).thenThrow(new RuntimeException("Error simulado"));
        RespuestaGeneralDTO respuesta = consultarEmpleadoService.consultarEmpleados();
        // Verificaciones
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatus());
        assertEquals(MENSAJE_ERROR_CONSULTA_EMPLEADOS, respuesta.getMessage());
        assertNull(respuesta.getData());

        // Verificar interacciones
        verify(empleadoRepository, times(1)).findAll();
        verifyNoInteractions(empleadoMapper); // El mapper no debe ser llamado

    }

}
