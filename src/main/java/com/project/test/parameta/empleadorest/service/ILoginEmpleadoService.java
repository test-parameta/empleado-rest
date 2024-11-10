package com.project.test.parameta.empleadorest.service;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;

/**
 * Interfaz para el servicio de autenticación de empleados.
 * <p>
 * Define el contrato para realizar el proceso de inicio de sesión
 * de un empleado en el sistema.
 * </p>
 */
public interface ILoginEmpleadoService {

    /**
     * Realiza el inicio de sesión de un empleado.
     *
     * @param loginEmpleadoDTO el objeto {@link LoginEmpleadoDTO} que contiene las credenciales
     *                         del empleado (correo y contraseña).
     * @return un objeto {@link RespuestaGeneralDTO} que contiene el estado, mensaje y datos
     *         del resultado de la operación, incluyendo un token de autenticación si el
     *         inicio de sesión es exitoso.
     */
    RespuestaGeneralDTO loginEmpleado(LoginEmpleadoDTO loginEmpleadoDTO);
}
