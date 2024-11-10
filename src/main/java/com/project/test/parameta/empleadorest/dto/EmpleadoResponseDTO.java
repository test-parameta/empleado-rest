package com.project.test.parameta.empleadorest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


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
