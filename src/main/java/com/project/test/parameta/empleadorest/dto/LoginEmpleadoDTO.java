package com.project.test.parameta.empleadorest.dto;

import lombok.Data;

/**
 * DTO que representa los datos necesarios para el inicio de sesión de un empleado.
 * <p>
 * Este objeto contiene las credenciales del empleado, como el correo electrónico
 * y la contraseña, requeridas para realizar la autenticación en el sistema.
 * </p>
 */
@Data
public class LoginEmpleadoDTO {

    private String correo;

    private String password;
}
