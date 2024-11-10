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

@Service
@Log4j2
public class ConsultarEmpleadoService implements IConsultarEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    private final EmpleadoMapper empleadoMapper;

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
