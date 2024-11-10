package com.project.test.parameta.empleadorest.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO que representa el tiempo de vinculación de un empleado con la compañía.
 * <p>
 * Este objeto contiene el detalle del tiempo de vinculación desglosado
 * en años, meses y días, proporcionando una representación clara y estructurada
 * de este período.
 * </p>
 */
@Data
@Builder
public class TiempoVinculacionCompaniaDTO {

    private String anios;

    private String meses;

    private String dias;

}
