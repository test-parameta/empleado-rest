package com.project.test.parameta.empleadorest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadorest.dto.EmpleadoRequestDTO;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.service.IGuardarEmpleadoService;
import com.project.test.parameta.empleadorest.service.ILoginEmpleadoService;
import com.project.test.parameta.empleadorest.service.impl.GuardarEmpleadoService;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoRequestMapper;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTest {

    @InjectMocks
    private EmpleadoController empleadoController;

    @Mock
    private IGuardarEmpleadoService empleadoService;

    @Mock
    private ILoginEmpleadoService iLoginEmpleadoService;

    @Spy
    private EmpleadoRequestMapper empleadoMapper;

    @Test
    void guardarEmpleadoExitoso() throws JAXBException, ParseException {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        respuestaGeneralDTO.setStatus(HttpStatus.OK);
        respuestaGeneralDTO.setMessage("Se guardo correctamente");
        EmpleadoRequestDTO empleadoRequestDTO = new EmpleadoRequestDTO();
        empleadoRequestDTO.setNombres("Uno");
        empleadoRequestDTO.setApellidos("Dos");
        empleadoRequestDTO.setSalario(23.0);
        empleadoRequestDTO.setFechaNacimiento("2024-11-02");
        empleadoRequestDTO.setFechaVinculacionComania("2020-12-01");
        empleadoRequestDTO.setCargo("GG");
        empleadoRequestDTO.setNumeroDocumento("1111");
        empleadoRequestDTO.setTipoDocumento("CC");
        when(empleadoService.validacionesEmpleado(any())).thenReturn(respuestaGeneralDTO);
        ResponseEntity<RespuestaGeneralDTO> response = empleadoController.guardarEmpleado(empleadoRequestDTO);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    void loginExitoso() {
        LoginEmpleadoDTO loginEmpleadoDTO = new LoginEmpleadoDTO();
        loginEmpleadoDTO.setCorreo("uno@gmail.com");
        loginEmpleadoDTO.setPassword("12345");
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        respuestaGeneralDTO.setStatus(HttpStatus.OK);
        when(iLoginEmpleadoService.loginEmpleado(any())).thenReturn(respuestaGeneralDTO);
        ResponseEntity<RespuestaGeneralDTO> response = empleadoController.loginEmpleado(loginEmpleadoDTO);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
