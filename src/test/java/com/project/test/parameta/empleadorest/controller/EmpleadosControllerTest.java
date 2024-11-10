package com.project.test.parameta.empleadorest.controller;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.service.IConsultarEmpleadoService;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmpleadosControllerTest {

    @InjectMocks private EmpleadosController empleadosController;

    @Mock
    private IConsultarEmpleadoService iConsultarEmpleadoService;

    @Test
    void consultarEmpledosExitoso() {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        respuestaGeneralDTO.setStatus(HttpStatus.OK);
        when(iConsultarEmpleadoService.consultarEmpleados()).thenReturn(respuestaGeneralDTO);
        ResponseEntity<RespuestaGeneralDTO> response = empleadosController.consultarEmpledos();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
