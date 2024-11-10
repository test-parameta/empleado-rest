package com.project.test.parameta.empleadorest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.entity.CargoEntity;
import com.project.test.parameta.commons.entity.TipoDocumentoEntity;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.commons.util.mappers.CargoMapper;
import com.project.test.parameta.commons.util.mappers.TipoDocumentoMapper;
import com.project.test.parameta.empleadorest.repository.CargoRepository;
import com.project.test.parameta.empleadorest.repository.EmpleadoRepository;
import com.project.test.parameta.empleadorest.repository.TipoDocumentoRepository;
import com.project.test.parameta.empleadorest.service.impl.GuardarEmpleadoService;
import com.project.test.parameta.empleadorest.soap.GuardarEmpleadoRequest;
import com.project.test.parameta.empleadorest.soap.GuardarEmpleadoResponse;
import com.project.test.parameta.empleadorest.soap.RespuestaGeneralElement;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoElementMapper;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuardarEmpleadoServiceTest {

    @InjectMocks private GuardarEmpleadoService service;

    @Mock private CargoRepository cargoRepository;

    @Mock private TipoDocumentoRepository tipoDocumentoRepository;

    @Mock @Qualifier(REST)
    private EmpleadoRepository empleadoRepository;

    @Mock
    private WebServiceTemplate webServiceTemplate;

    @Spy
    private EmpleadoElementMapper empleadoElementMapper;

    @Mock
    private CargoMapper cargoMapper;

    @Mock
    private TipoDocumentoMapper tipoDocumentoMapper;

    @Spy
    private EmpleadoRequestMapper empleadoRequestMapper;

    private EmpleadoDTO empleadoDTO;

    @BeforeEach
    void setUp() throws IOException {
        empleadoDTO = Utilidades.convertJsonToDto(new File("src/test/resources/registrar-empleado.json"), EmpleadoDTO.class);
    }

    @Test
    void guardarEmpleadoExitoso() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RespuestaGeneralElement respuesteGeneral = new RespuestaGeneralElement();
        respuesteGeneral.setData(objectMapper.writeValueAsString(empleadoDTO));
        GuardarEmpleadoResponse response = new GuardarEmpleadoResponse();
        response.setRespuesta(respuesteGeneral);
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setIdCargo(1);
        cargoEntity.setNombreCargo(CargoEnum.GG);
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity();
        tipoDocumentoEntity.setIdTipoDocumento(1);
        tipoDocumentoEntity.setNombreTipoDocumento(TipoDocumentoEnum.CC);
        when(empleadoRepository.existsByNumeroDocumentoEmpleado(any())).thenReturn(false);
        when(cargoRepository.findByNombreCargo(any())).thenReturn(Optional.of(cargoEntity));
        when(tipoDocumentoRepository.buscarTipoDocumentoPorNombre(any())).thenReturn(Optional.of(tipoDocumentoEntity));
        when(cargoMapper.entityToDto(cargoEntity)).thenReturn(empleadoDTO.getCargoFk());
        when(tipoDocumentoMapper.entityToDto(tipoDocumentoEntity)).thenReturn(empleadoDTO.getTipoDocumentoFk());
        when(webServiceTemplate.marshalSendAndReceive(any(GuardarEmpleadoRequest.class))).thenReturn(response);
        RespuestaGeneralDTO respuestaGeneralDTO = service.validacionesEmpleado(empleadoDTO);
        assertEquals(HttpStatus.OK, respuestaGeneralDTO.getStatus());
        assertNotNull(respuestaGeneralDTO.getData());
        assertEquals(MENSAJE_OK_GUARDAR_EMPLEADO, respuestaGeneralDTO.getMessage());
    }

    @Test
    void guardarEmpleadoFallido() throws JsonProcessingException {
        // Mock para simular una excepción
        when(empleadoRepository.existsByNumeroDocumentoEmpleado(empleadoDTO.getNumeroDocumentoEmpleado()))
                .thenReturn(false);
        when(cargoRepository.findByNombreCargo(empleadoDTO.getCargoFk().getNombreCargo()))
                .thenThrow(new RuntimeException("Error simulado"));

        RespuestaGeneralDTO respuesta = service.validacionesEmpleado(empleadoDTO);

        // Verificaciones
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatus());
        assertEquals(ERROR_PROCESAR_SOLICITUD, respuesta.getMessage());
        assertNull(respuesta.getData());

        // Verificar interacciones
        verify(empleadoRepository, times(1)).existsByNumeroDocumentoEmpleado(empleadoDTO.getNumeroDocumentoEmpleado());
        verify(cargoRepository, times(1)).findByNombreCargo(empleadoDTO.getCargoFk().getNombreCargo());
        verifyNoInteractions(tipoDocumentoRepository, empleadoElementMapper, webServiceTemplate);

        // Mock para simular que el empleado ya existe
        when(empleadoRepository.existsByNumeroDocumentoEmpleado(empleadoDTO.getNumeroDocumentoEmpleado()))
                .thenReturn(true);

        // Llamar al método
        respuesta = service.validacionesEmpleado(empleadoDTO);

        // Verificaciones
        assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatus());
        assertEquals(PERSONA_EXISTE_SISTEMA, respuesta.getMessage());
        assertNull(respuesta.getData());
    }

}
