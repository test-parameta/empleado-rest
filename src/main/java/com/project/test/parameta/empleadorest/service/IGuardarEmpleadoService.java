package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import jakarta.xml.bind.JAXBException;

public interface IGuardarEmpleadoService {

    RespuestaGeneralDTO validacionesEmpleado(EmpleadoDTO empleadoDTO) throws JAXBException;
}
