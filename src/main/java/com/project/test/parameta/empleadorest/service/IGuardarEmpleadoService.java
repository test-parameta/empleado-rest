package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import jakarta.xml.bind.JAXBException;

/**
 * Interfaz para el servicio de validación y guardado de empleados.
 * <p>
 * Define el contrato para realizar las validaciones necesarias y guardar
 * la información de un empleado en el sistema.
 * </p>
 */
public interface IGuardarEmpleadoService {

    /**
     * Realiza las validaciones necesarias para un empleado y procede con su guardado.
     *
     * @param empleadoDTO el objeto {@link EmpleadoDTO} que contiene la información del empleado a validar y guardar.
     * @return un objeto {@link RespuestaGeneralDTO} que contiene el estado, mensaje y datos del resultado de la operación.
     * @throws JAXBException si ocurre un error al procesar datos XML durante la operación.
     */
    RespuestaGeneralDTO validacionesEmpleado(EmpleadoDTO empleadoDTO) throws JAXBException;
}
