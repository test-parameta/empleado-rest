package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;

public interface IEmpleadoService {

    RespuestaGeneralDTO validacionesEmpleado(EmpleadoDTO empleadoDTO);
}
