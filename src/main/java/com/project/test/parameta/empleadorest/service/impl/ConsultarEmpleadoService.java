package com.project.test.parameta.empleadorest.service.impl;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadorest.repository.EmpleadoRepository;
import com.project.test.parameta.empleadorest.service.IConsultarEmpleadoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.*;

/**
 * Implementación del servicio para consultar empleados.
 * <p>
 * Esta clase se encarga de obtener la información de todos los empleados
 * registrados en el sistema y mapearlos a DTOs para su presentación.
 * </p>
 */
@Service
@Log4j2
public class ConsultarEmpleadoService implements IConsultarEmpleadoService {

    /**
     * Repositorio para acceder a los datos de los empleados.
     */
    private final EmpleadoRepository empleadoRepository;

    /**
     * Mapper para transformar las entidades de empleado a DTOs.
     */
    private final EmpleadoMapper empleadoMapper;

    /**
     * Constructor que inyecta las dependencias necesarias.
     *
     * @param empleadoRepository el repositorio de empleados, calificado con {@link Qualifier} para identificar su implementación.
     * @param empleadoMapper     el mapper para convertir entidades de empleados a DTOs.
     */
    public ConsultarEmpleadoService(
            @Qualifier(REST) EmpleadoRepository empleadoRepository,
            EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public RespuestaGeneralDTO consultarEmpleados() {
        RespuestaGeneralDTO respuesta = new RespuestaGeneralDTO();
        try{
            respuesta.setData(empleadoMapper.listEntityToListDto(empleadoRepository.findAll()));
            respuesta.setMessage(CONSULTA_CORRECTA_EMPLEADOS);
            respuesta.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(ERROR_CONSULTA_EMPLEADOS, ex.getMessage());
            respuesta.setMessage(MENSAJE_ERROR_CONSULTA_EMPLEADOS);
            respuesta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }
}
