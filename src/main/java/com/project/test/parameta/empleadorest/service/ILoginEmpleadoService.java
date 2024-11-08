package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;

public interface ILoginEmpleadoService {

    RespuestaGeneralDTO loginEmpleado(LoginEmpleadoDTO loginEmpleadoDTO);
}
