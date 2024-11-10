package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;

/**
 * Interfaz para el servicio de consulta de empleados.
 * <p>
 * Define el contrato para obtener información de los empleados registrados en el sistema.
 * </p>
 */
public interface IConsultarEmpleadoService {

    /**
     * Realiza la consulta de todos los empleados registrados.
     *
     * @return un objeto {@link RespuestaGeneralDTO} que contiene el estado,
     *         mensaje y los datos de la consulta de empleados.
     */
    RespuestaGeneralDTO consultarEmpleados();

}
