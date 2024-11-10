package com.project.test.parameta.empleadorest.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO que representa la edad actual de un empleado.
 * <p>
 * Este objeto contiene la información de la edad desglosada en años, meses y días,
 * para su uso en respuestas o cálculos relacionados con empleados.
 * </p>
 */
@Data
@Builder
public class EdadActualEmpleadoDTO {

    private String anios;

    private String meses;

    private String dias;

}
