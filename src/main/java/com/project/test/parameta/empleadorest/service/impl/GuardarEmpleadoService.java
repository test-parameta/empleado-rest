package com.project.test.parameta.empleadorest.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.util.mappers.CargoMapper;
import com.project.test.parameta.commons.util.mappers.TipoDocumentoMapper;
import com.project.test.parameta.empleadorest.dto.EdadActualEmpleadoDTO;
import com.project.test.parameta.empleadorest.dto.EmpleadoResponseDTO;
import com.project.test.parameta.empleadorest.dto.TiempoVinculacionCompaniaDTO;
import com.project.test.parameta.empleadorest.repository.CargoRepository;
import com.project.test.parameta.empleadorest.repository.EmpleadoRepository;
import com.project.test.parameta.empleadorest.repository.TipoDocumentoRepository;
import com.project.test.parameta.empleadorest.service.IGuardarEmpleadoService;

import com.project.test.parameta.empleadorest.soap.*;
import com.project.test.parameta.empleadorest.utils.constants.Constantes;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoElementMapper;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoRequestMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.*;

/**
 * Servicio para gestionar la validación y guardado de empleados.
 * <p>
 * Este servicio implementa la interfaz {@link IGuardarEmpleadoService}
 * y se encarga de realizar las validaciones necesarias, invocar servicios SOAP
 * y calcular detalles adicionales como tiempo de vinculación y edad actual del empleado.
 * </p>
 */
@Service
@Log4j2
public class GuardarEmpleadoService implements IGuardarEmpleadoService {

    /**
     * Plantilla para realizar llamadas a servicios SOAP.
     */
    private final WebServiceTemplate webServiceTemplate;

    /**
     * Mapper para transformar DTOs de empleados en elementos SOAP.
     */
    private final EmpleadoElementMapper empleadoElementMapper;

    /**
     * Repositorio para gestionar datos relacionados con los cargos.
     */
    private final CargoRepository cargoRepository;

    /**
     * Repositorio para gestionar datos relacionados con los tipos de documento.
     */
    private final TipoDocumentoRepository tipoDocumentoRepository;

    /**
     * Mapper para transformar entidades de cargos a DTOs.
     */
    private final CargoMapper cargoMapper;

    /**
     * Mapper para transformar entidades de tipos de documento a DTOs.
     */
    private final TipoDocumentoMapper tipoDocumentoMapper;

    /**
     * Repositorio para gestionar datos de empleados.
     */
    private final EmpleadoRepository empleadoRepository;

    /**
     * Mapper para transformar solicitudes de empleados en DTOs.
     */
    private final EmpleadoRequestMapper empleadoRequestMapper;

    /**
     * Constructor para inyectar las dependencias necesarias.
     *
     * @param webServiceTemplate    plantilla para llamadas SOAP.
     * @param empleadoElementMapper mapper para elementos SOAP de empleados.
     * @param cargoRepository       repositorio de cargos.
     * @param tipoDocumentoRepository repositorio de tipos de documento.
     * @param cargoMapper           mapper de cargos.
     * @param tipoDocumentoMapper   mapper de tipos de documento.
     * @param empleadoRepository    repositorio de empleados, identificado con {@link Qualifier}.
     * @param empleadoRequestMapper mapper de solicitudes de empleados.
     */
    public GuardarEmpleadoService(WebServiceTemplate webServiceTemplate,
                                  EmpleadoElementMapper empleadoElementMapper,
                                  CargoRepository cargoRepository,
                                  TipoDocumentoRepository tipoDocumentoRepository,
                                  CargoMapper cargoMapper,
                                  TipoDocumentoMapper tipoDocumentoMapper,
                                  @Qualifier(REST) EmpleadoRepository empleadoRepository,
                                  EmpleadoRequestMapper empleadoRequestMapper) {
        this.webServiceTemplate = webServiceTemplate;
        this.empleadoElementMapper = empleadoElementMapper;
        this.cargoRepository = cargoRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.cargoMapper = cargoMapper;
        this.tipoDocumentoMapper = tipoDocumentoMapper;
        this.empleadoRepository = empleadoRepository;
        this.empleadoRequestMapper = empleadoRequestMapper;
    }

    @Override
    public RespuestaGeneralDTO validacionesEmpleado(EmpleadoDTO empleadoDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        Boolean existe = empleadoRepository.existsByNumeroDocumentoEmpleado(empleadoDTO.getNumeroDocumentoEmpleado());
        if (!existe && validarMayorEdad(empleadoDTO.getFechaNacimientoEmpleado())) {
            ObjectMapper objectMapper = new ObjectMapper();
            GuardarEmpleadoRequest request = new GuardarEmpleadoRequest();
            EmpleadoResponseDTO response;
            try {
                empleadoDTO.setCargoFk(cargoMapper.entityToDto(cargoRepository.findByNombreCargo(empleadoDTO.getCargoFk().getNombreCargo()).orElse(null)));
                empleadoDTO.setTipoDocumentoFk(tipoDocumentoMapper.entityToDto(tipoDocumentoRepository.buscarTipoDocumentoPorNombre(empleadoDTO.getTipoDocumentoFk().getNombreTipoDocumento()).orElse(null)));
                EmpleadoElement empleadoElement = empleadoElementMapper.dtoToElement(empleadoDTO);
                request.setEmpleado(empleadoElement);
                GuardarEmpleadoResponse responseSoap = (GuardarEmpleadoResponse) webServiceTemplate.marshalSendAndReceive(request);
                empleadoDTO = objectMapper.readValue(responseSoap.getRespuesta().getData(), EmpleadoDTO.class);
                response = empleadoRequestMapper.dtoToResponse(empleadoDTO);
                calcularTiempoVinculacion(empleadoDTO, response);
                calcularEdadActualEmpleado(empleadoDTO, response);
                respuestaGeneralDTO.setData(response);
                respuestaGeneralDTO.setStatus(HttpStatus.OK);
                respuestaGeneralDTO.setMessage(Constantes.MENSAJE_OK_GUARDAR_EMPLEADO);
            } catch (Exception ex) {
                log.error(MENSAJE_ERROR_GUARDAR_EMPLEADO, ex.getMessage());
                respuestaGeneralDTO.setMessage(ERROR_PROCESAR_SOLICITUD);
                respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            respuestaGeneralDTO.setMessage(existe ? PERSONA_EXISTE_SISTEMA : PERSONA_NO_MAYOR_EDAD);
            respuestaGeneralDTO.setStatus(HttpStatus.BAD_REQUEST);
        }
        return respuestaGeneralDTO;
    }

    /**
     * Calcula el tiempo de vinculación de un empleado con la compañía.
     *
     * @param empleadoDTO el DTO que contiene los datos del empleado.
     * @param response    el DTO de respuesta donde se establecerá el tiempo de vinculación.
     */
    private void calcularTiempoVinculacion(EmpleadoDTO empleadoDTO, EmpleadoResponseDTO response){
        // Fechas actuales y de vinculación
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaVinculacion = empleadoDTO.getFechaVinculacionCompaniaEmpleado().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Calcular la diferencia en años, meses y días
        Period periodoVinculacion = Period.between(fechaVinculacion, fechaActual);

        // Calcular meses totales y días totales
        long mesesTotales = ChronoUnit.MONTHS.between(fechaVinculacion, fechaActual);
        long diasTotales = ChronoUnit.DAYS.between(fechaVinculacion, fechaActual);

        // Crear DTO con el tiempo de vinculación
        TiempoVinculacionCompaniaDTO tiempoVinculacionCompaniaDTO = TiempoVinculacionCompaniaDTO.builder()
                .anios(String.valueOf(periodoVinculacion.getYears()))
                .meses(String.valueOf(mesesTotales))
                .dias(String.valueOf(diasTotales))
                .build();

        // Establecer el tiempo de vinculación en el DTO
        response.setTiempoVinculacionCompania(tiempoVinculacionCompaniaDTO);

    }

    /**
     * Calcula la edad actual de un empleado.
     *
     * @param empleadoDTO el DTO que contiene los datos del empleado.
     * @param response    el DTO de respuesta donde se establecerá la edad actual.
     */
    private void calcularEdadActualEmpleado(EmpleadoDTO empleadoDTO, EmpleadoResponseDTO response){
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = empleadoDTO.getFechaNacimientoEmpleado().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        long mesesTotales = ChronoUnit.MONTHS.between(fechaNacimiento, fechaActual);
        long diasTotales = ChronoUnit.DAYS.between(fechaNacimiento, fechaActual);
        EdadActualEmpleadoDTO edadActualEmpleadoDTO = EdadActualEmpleadoDTO.builder()
                .anios(String.valueOf(periodo.getYears()))
                .meses(String.valueOf(mesesTotales))
                .dias(String.valueOf(diasTotales))
                .build();
        response.setEdadActualEmpleado(edadActualEmpleadoDTO);

    }

    /**
     * Verifica si un empleado es mayor de edad según su fecha de nacimiento.
     *
     * @param fechaNacimiento la fecha de nacimiento del empleado.
     * @return {@code true} si el empleado es mayor de edad, {@code false} en caso contrario.
     */
    private boolean validarMayorEdad(Date fechaNacimiento) {
        // Obtener la fecha actual
        Calendar fechaActual = Calendar.getInstance();

        // Configurar el calendario para la fecha de nacimiento
        Calendar fechaNacimientoCal = Calendar.getInstance();
        fechaNacimientoCal.setTime(fechaNacimiento);

        // Calcular la diferencia en años
        int edad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCal.get(Calendar.YEAR);

        // Ajustar si el cumpleaños no ha ocurrido aún este año
        if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNacimientoCal.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        // Retornar si es mayor o igual a 18 años
        return edad >= EDAD_MAYOR;
    }

}
