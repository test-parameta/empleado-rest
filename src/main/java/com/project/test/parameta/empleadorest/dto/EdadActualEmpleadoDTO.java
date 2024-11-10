package com.project.test.parameta.empleadorest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EdadActualEmpleadoDTO {

    private String anios;

    private String meses;

    private String dias;

}
