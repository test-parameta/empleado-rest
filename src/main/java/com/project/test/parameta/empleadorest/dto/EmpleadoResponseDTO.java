package com.project.test.parameta.empleadorest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * DTO que representa la respuesta con los datos completos de un empleado.
 * <p>
 * Esta clase extiende {@link EmpleadoRequestDTO} e incluye información adicional
 * como el correo, contraseña, tiempo de vinculación con la compañía y la edad
 * actual del empleado.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmpleadoResponseDTO extends EmpleadoRequestDTO {

    private String correo;

    private String password;

    private TiempoVinculacionCompaniaDTO tiempoVinculacionCompania;

    private EdadActualEmpleadoDTO edadActualEmpleado;

}
